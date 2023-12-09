package adventofcode2023.day8

import common.Program
import common.parseGroupsList

class ProgramDay8(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val network = brutInputs.parseGroupsList().let { Network.build(it) }

    override fun part1(): String {
        val endNode = network.nodes.first { it.name == "ZZZ" }
        val currentNode = network.nodes.first { it.name == "AAA" }
        val instructionCount = findEndNode(listOf(currentNode), listOf(endNode))

        return instructionCount.toString()
    }

    override fun part2(): String {
        val endNodes = network.nodes.filter { it.name.endsWith("Z") }
        val startNodes = network.nodes.filter { it.name.endsWith("A") }

        val counter = findEndNode(startNodes, endNodes)

        return counter.toString()
    }

    private data class InstructionNodeMapping(
        val instruction: Instruction,
        val node: Node,
    )

    private data class InstructionNode(
        val instruction: Instruction,
        val node: Node,
    )

    private data class EndInstructionNode(
        val endNode: Node,
        val nbInstructionToJoinEndNode: Long,
        val endInstruction: Instruction,
    )

    private fun findEndNode(currentNodes: List<Node>, lastNodes: List<Node>): Long {
        var currentNodes1 = currentNodes
        var instructionCount = 0L
        val mappings = network.instructions.flatMap { inst ->
            network.nodes.map {
                InstructionNodeMapping(inst, it) to mutableMapOf<Long, Node>()
            }
        }.toMap()

        val mappingsFirst = mutableMapOf<InstructionNode, EndInstructionNode>()

        var instruction = network.instructions.first()

        while (!currentNodes1.all { lastNodes.contains(it) }) {
            var firstNode = currentNodes1.first()
            var firstNodeInstructionCounter = instructionCount
            var firstNodeInstruction = instruction

            val firstMapping = mappingsFirst[InstructionNode(firstNodeInstruction, firstNode)]

            if (firstMapping != null) {
                firstNodeInstructionCounter += firstMapping.nbInstructionToJoinEndNode
                instruction = firstMapping.endInstruction
                firstNode = firstMapping.endNode
            } else {
                while (!lastNodes.contains(firstNode) || instructionCount == firstNodeInstructionCounter) {
                    val firstMappingAgain = mappingsFirst[InstructionNode(firstNodeInstruction, firstNode)]
                    if (firstMappingAgain != null) {
                        firstNodeInstructionCounter += firstMappingAgain.nbInstructionToJoinEndNode
                        instruction = firstMappingAgain.endInstruction
                        firstNode = firstMappingAgain.endNode
                        break
                    } else {
                        val nextNode = when (firstNodeInstruction.side) {
                            "L" -> firstNode.left
                            "R" -> firstNode.right
                            else -> error("prout")
                        }

                        firstNodeInstructionCounter++
                        firstNode = nextNode!!
                        firstNodeInstruction = firstNodeInstruction.next!!
                    }
                }

                mappingsFirst[InstructionNode(instruction, currentNodes1.first())] =
                    EndInstructionNode(firstNode, firstNodeInstructionCounter - instructionCount, firstNodeInstruction)
            }

            val currentNbInstruction = firstNodeInstructionCounter - instructionCount

            currentNodes1.first().also { node ->
                mappings[InstructionNodeMapping(instruction, node)]!![currentNbInstruction] = firstNode
            }

            var otherNodes = currentNodes1.subList(1, currentNodes1.size)

            otherNodes = otherNodes.asSequence().map {
                var otherInstructionCount = instructionCount
                var otherInstruction = instruction.copy()
                var otherNode = it
                val mapping = mappings[InstructionNodeMapping(otherInstruction, otherNode)]!!
                if (mapping.contains(currentNbInstruction)) {
                    mapping[currentNbInstruction]!!
                } else {
                    while (otherInstructionCount != firstNodeInstructionCounter) {
                        val nextNode = when (otherInstruction.side) {
                            "L" -> otherNode.left
                            "R" -> otherNode.right
                            else -> error("prout")
                        }

                        otherInstructionCount++
                        otherInstruction = otherInstruction.next!!
                        otherNode = nextNode!!
                    }
                    mapping[currentNbInstruction] = otherNode
                    otherNode
                }
            }.toList()

            instructionCount = firstNodeInstructionCounter
            currentNodes1 = listOf(firstNode) + otherNodes
        }

        return instructionCount
    }
}

private data class Network(val instructions: List<Instruction>, val nodes: List<Node>) {
    companion object {
        fun build(inputs: List<List<String>>): Network {
            val instructions =
                inputs[0][0].mapIndexed { index, side -> Instruction(side.toString(), index) }.also { instructions ->
                    instructions.forEachIndexed { index, instruction ->
                        if (index == instructions.size - 1)
                            instruction.next = instructions.first()
                        else
                            instruction.next = instructions[index + 1]
                    }
                }

            val nodes = inputs[1].map { Node.build(it) }.also { stringNodes ->
                stringNodes.also { nodes ->
                    nodes.forEach { node ->
                        node.right = nodes.first { it.name == node.rightName }
                        node.left = nodes.first { it.name == node.leftName }
                    }
                }
            }

            return Network(instructions, nodes)
        }
    }
}

private data class Instruction(val side: String, val index: Int, var next: Instruction? = null) {
    override fun toString(): String {
        return "Instruction(side='$side', index='$index', next=${next!!.side})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Instruction

        if (side != other.side) return false
        if (index != other.index) return false

        return true
    }

    override fun hashCode(): Int {
        var result = side.hashCode()
        result = 31 * result + index
        return result
    }
}

private data class Node(
    val name: String,
    val leftName: String,
    val rightName: String,
    var left: Node? = null,
    var right: Node? = null
) {
    companion object {
        val regex = "([A-Z0-9]*) = \\(([A-Z0-9]*), ([A-Z0-9]*)\\)".toRegex()
        fun build(inputs: String): Node {
            val values = regex.find(inputs)!!.groupValues
            return Node(name = values[1], leftName = values[2], rightName = values[3])
        }
    }


    override fun toString(): String {
        return "Node(name='$name', left='$leftName', right='$rightName')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (name != other.name) return false
        if (leftName != other.leftName) return false
        if (rightName != other.rightName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + leftName.hashCode()
        result = 31 * result + rightName.hashCode()
        return result
    }
}
