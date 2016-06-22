package arma.ark.cartograph.mission.world

data class World(
    val name: String,
    val displayName: String,
    val width: Int,
    val height: Int,
    val minZoom: Int,
    val maxZoom: Int
)

val DEFAULT_WORLD = World(name = "Empty", displayName = "Empty", width = 1, height = 1,  minZoom = 1, maxZoom = 1)
val WORLDS_ARRAY = arrayOf(
    World(name = "Altis",                   displayName = "Altis",                      width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Bootcamp_ACR",            displayName = "Bukovina",                   width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Caribou",                 displayName = "Caribou Frontier",           width = 8192,   height = 8192,  minZoom = 1, maxZoom = 6),
    World(name = "clafghan",                displayName = "CLA Clafghan",               width = 20480,  height = 20480, minZoom = 1, maxZoom = 7),
    World(name = "Chernarus",               displayName = "Chernarus",                  width = 15360,  height = 15360, minZoom = 1, maxZoom = 6),
    World(name = "Chernarus_Summer",        displayName = "Summer Chernarus",           width = 15360,  height = 15360, minZoom = 1, maxZoom = 6),
    World(name = "Chernarus_winter",        displayName = "Chernarus Winter",           width = 15360,  height = 15360, minZoom = 1, maxZoom = 6),
    World(name = "Desert_E",                displayName = "Desert",                     width = 2048,   height = 2048,  minZoom = 1, maxZoom = 4),
    World(name = "FDF_Isle1_a",             displayName = "Podagorsk",                  width = 20480,  height = 20480, minZoom = 1, maxZoom = 7),
    World(name = "imrali",                  displayName = "Imrali Island",              width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "imralispring",            displayName = "Imrali Island - Spring",     width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "mbg_celle2",              displayName = "Celle 2",                    width = 12288,  height = 12288, minZoom = 1, maxZoom = 6),
    World(name = "MCN_Aliabad",             displayName = "Aliabad Region",             width = 5120,   height = 5120,  minZoom = 1, maxZoom = 5),
    World(name = "namalsk",                 displayName = "Namalsk",                    width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Porto",                   displayName = "Porto",                      width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "ProvingGrounds_P",        displayName = "Proving Grounds",            width = 2048,   height = 2048,  minZoom = 1, maxZoom = 4),
    World(name = "Sara",                    displayName = "Sahrani",                    width = 20480,  height = 20480, minZoom = 1, maxZoom = 7),
    World(name = "Shapur_BAF",              displayName = "Shapur",                     width = 2048,   height = 2048,  minZoom = 1, maxZoom = 4),
    World(name = "Stratis",                 displayName = "Stratis",                    width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Takistan",                displayName = "Takistan",                   width = 12800,  height = 12800, minZoom = 1, maxZoom = 6),
    World(name = "Thirsk",                  displayName = "Thirsk",                     width = 5120,   height = 5120,  minZoom = 1, maxZoom = 5),
    World(name = "ThirskW",                 displayName = "Thirsk Winter",              width = 5120,   height = 5120,  minZoom = 1, maxZoom = 5),
    World(name = "torabora",                displayName = "ToraBora",                   width = 10240,  height = 10240, minZoom = 1, maxZoom = 6),
    World(name = "utes",                    displayName = "Utes",                       width = 5120,   height = 5120,  minZoom = 1, maxZoom = 5),
    World(name = "VR",                      displayName = "Virtual Reality",            width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Woodland_ACR",            displayName = "Bystrica",                   width = 1000,   height = 1000,  minZoom = 1, maxZoom = 6),
    World(name = "Zargabad",                displayName = "Zargabad",                   width = 8192,   height = 8192,  minZoom = 1, maxZoom = 6)
)



val WORLDS =  WORLDS_ARRAY.associateBy { w -> w.name.toLowerCase() }