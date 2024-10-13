package advanced_concepts.collection_builders

var toolbox = mapOf(
    "Nail" to "Hammer",
    "Hex Nut" to "Wrench",
    "Hex Bolt" to "Wrench",
    "Slotted Screw" to "Slotted Screwdriver",
    "Phillips Screw" to "Phillips Screwdriver",
)

class Tool(
    val name: String,
    val weightInOunces: Int,
    val correspondingHardware: String,
)

val tools = listOf(
    Tool("Hammer", 14, "Nail"),
    Tool("Wrench", 8, "Hex Nut"),
    Tool("Wrench", 8, "Hex Bolt"),
    Tool("Slotted Screwdriver", 5, "Slotted Screw"),
    Tool("Phillips Screwdriver", 5, "Phillips Screw"),
)

fun main() {
    println(pair1())
    println(pair2())

    toolbox = toolbox + Pair("Hex Bolt", "Nut Driver")
    toolbox = toolbox - "Lumber"

    val newToolbox = toolbox
        .mapKeys { entry -> entry.key.replace("Hex", "Flange") }
        .mapValues { entry -> entry.value.replace("Wrench", "Ratchet") }

    println(newToolbox)

    // Convert a List<Tool> to a Map<String, String>
    val associate = tools.associate { tool ->
        tool.correspondingHardware to tool.name
    }
    println(associate)

    // Convert a List<Tool> to a Map<String, Tool>
    val toolsByName = tools.associateBy { tool -> tool.name }
    println(toolsByName)

    // Convert a List<Tool> to a Map<Tool, Double>
    val toolWeightInPounds = tools.associateWith { tool ->
        tool.weightInOunces * 0.0625
    }
    println(toolWeightInPounds)

    // Convert a List<Tool> to a  Map<Int, List<Tool>>
    val toolsByWeight = tools.groupBy { tool ->
        tool.weightInOunces
    }
    println(toolsByWeight)

    // Convert a List<Tool> to a  Map<Int, List<String>>
    val toolNamesByWeight = tools.groupBy(
        { tool -> tool.weightInOunces },
        { tool -> tool.name }
    )
    println(toolNamesByWeight)
}

fun pair1(): Pair<String, String> {
    return Pair("Nail1", "Hammer")
}

fun pair2(): Pair<String, String> {
    return "Nail2" to "Hammer"
}