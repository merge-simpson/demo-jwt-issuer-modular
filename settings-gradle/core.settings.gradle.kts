val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)

include(
    ":jwt-api",
    ":jwt-issuer",
)

project(":jwt-api").projectDir = core["jwt-api"]!!
project(":jwt-issuer").projectDir = core["jwt-issuer"]!!