import groovy.text.SimpleTemplateEngine;

def printPrefix = "[INFO] GROOVY>"
def templatePath = "${project.basedir}/src/main/docker/DockerfileTemplate"
def targetDir = "${project.basedir}/target/dockerfile"

// create Dockerfile content
def bindings = [
        fileName: project.build.finalName
]
def dockerfile = new SimpleTemplateEngine().createTemplate(new File(templatePath).getText()).make(bindings)

// create target directory
println "${printPrefix} writing dir ${targetDir}"
new File(targetDir).mkdirs()

// write Dockerfile
println "${printPrefix} writing Dockerfile"
new File("${targetDir}/Dockerfile").withWriter('UTF-8') { writer -> writer.write(dockerfile) }
