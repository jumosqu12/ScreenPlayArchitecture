# Scaffolding of ScreenPlay Architecture

The Gradle plugin enables the creation of Java applications based on the Screenplay Architecture, adhering to our 
best practices.

Bancolombia's technical team has developed the Screenplay Architecture plugin as a tool designed for programmers, 
organizations, and anyone seeking to implement test automation using the Screenplay design pattern while following 
programming best practices, including SOLID principles.

The Screenplay Architecture plugin is open-source, allowing the programming community to contribute to its development. 
These contributions may include improvements, new practices, added functionalities, and other enhancements that make the 
plugin a robust and valuable tool for the entire community.

## License
This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for detail.

- [Scaffolding of ScreenPlay Architecture](#Scaffolding-of-ScreenPlay-Architecture)
- [Plugin Implementation](#plugin-implementation)
- [Tasks](#tasks)
    - [Generate Project](#generate-project)
    - [Generate Features](#generate-features)
    - [Generate Runners](#generate-runners)
    - [Generate Rest Interaction](#generate-rest-interaction)
    - [Generate Tasks](#generate-tasks)
    - [Generate Pipeline](#generate-pipeline)
    - [Generate Database connection](#generate-database-connection)
    - [Generate Critical Root](#generate-critical-root)
- [How can I help?](#how-can-i-help)
- [What's Next?](#whats-next)

# Plugin implementation

To use the [plugin](#) ensure you have Gradle version 8.6 or later. To get started, add the following section to your
**build.gradle** file.

```groovy
plugins {
    id "io.github.jumosqu12.screenplayarchitecture" version "1.1.1"
}
```

If it is a new project, execute this script in the root directory of your project.

```sh
echo "plugins {
  id \"io.github.jumosqu12.screenplayarchitecture\" version \"1.1.1\"
}" > build.gradle
```

# How do it?
<img src="./assets/Frame1CreateProject.gif" alt="Demostración del uso" width="80%"/>

# Tasks

The Scaffolding Screenplay Architecture plugin enables you to run 8 tasks:

## Generate Project

The **`screenPlayArchitecture | spa`** task generates a Screenplay Architecture structure in your project. 
This task includes three optional parameters: `projectName` , `groupId` and `principalPackage`.
If you run this task in an existing project, it will override the `build.gradle` file.

- **`projectName`** `= ProjectName`: This parameter is going to specify name of the
  project. `Default value = Screenplay_architecture`
- **`groupId`** `= <your.company.domain>`: You can specify your domain, this parameter going to use for package
  structure. `Default value = co.com.bancolombia.certificacion`
- **`principalPackage`** `= package container`: This parameter acts as a package container and complements the.
 `Default value = screen`
- **`type`** `= choose between REST or UX`: This parameter going to specify type automation will be
  create. `Default value = UX`

```shell
gradle screenPlayArchitecture --projectName=Team_moduloPrueba --groupId=co.com.bancolombia.certificacion --principalPackage=moduloprueba --type=REST
gradle spa --projectName=Team_moduloPrueba --groupId=co.com.bancolombia.certificacion --principalPackage=moduloprueba --type=REST
```

**_The structure will look like this for java:_**

```bash
   📦NameProject
   ┣ 📂src
   ┃ ┣ 📂main
   ┃ ┃ ┗ 📂java
   ┃ ┃   ┗ 📂co
   ┃ ┃     ┗ 📂com
   ┃ ┃       ┗ 📂bancolombia
   ┃ ┃         ┗ 📂certificacion
   ┃ ┃           ┗ 📂[principalPackage]
   ┃ ┃             ┣ 📂exceptions
   ┃ ┃             ┣ 📂integrations
   ┃ ┃             ┣ 📂interactions
   ┃ ┃             ┣ 📂models
   ┃ ┃             ┣ 📂questions
   ┃ ┃             ┣ 📂tasks
   ┃ ┃             ┣ 📂userinterfaces
   ┃ ┃             ┗ 📂utils
   ┃ ┗ 📂test
   ┃   ┣ 📂java
   ┃   ┃ ┗ 📂co
   ┃   ┃   ┗ 📂com
   ┃   ┃     ┗ 📂bancolombia
   ┃   ┃       ┗ 📂certificacion
   ┃   ┃         ┗ 📂[principalPackage]
   ┃   ┃           ┣ 📂runners
   ┃   ┃           ┗ 📂stepdefinitions
   ┃   ┗ 📂resources          
   ┃     ┗ 📂features         
   ┣ 📜build.gradle
   ┗ 📜settings.gradle
   ```
# How do it?
<img src="./assets/Frame2GenerateProject.gif" alt="Demostración del uso" width="80%"/>

## Generate Features

The **`generateFeature | gft`** task will generate feature files inside features folder, this task has two required
parameter `name` and `nameSubFolder`
also, there are 1 parameters optional `examples`

- **`name`** `= name_feature`: This parameter is going to specify the name of the feature file. `field is required`
- **`examples`** `= true`: This parameter is going to specify if Scenario Outline are
  needed. `field for default is false`
- **`nameSubFolder`** `= nameSubFolder`: This parameter is going to specify the name of the subfolder that will contain
  the feature files. `field is requered.`

```shell
  gradle generateFeature --name=feature_name --nameSubFolder=[nameSubFolder] --examples=[optionalBoolValue] 
  gradle gft --name=feature_name --nameSubFolder=[nameSubFolder] --examples=[optionalBoolValue] 
```

```bash
   📂test
   ┃   ┣ 📂java
   ┃   ┗ 📂resources          
   ┃     ┗ 📂features
   ┃       ┗ 📂[nameSubFolder]
   ┃         ┗ 📜[feature_name].feature
```
# How do it?
<img src="./assets/Frame3GenerateFeature.gif" alt="Demostración del uso" width="80%"/>

## Generate Runners

The **`generateRunner | grun`** task will generate runners classes inside runners package, this task has one required
parameter `name`

- **`name`** `= NameRunner`: This parameter is going to specify the name of the runner class. `field is required`
- **`folderName`** `= package name`: This parameter is going to specify the name of the package that will contain the
  runners classes. `field is required`

```shell
  gradle generateRunner --name=runnerClassName --folderName=runnerspackage
  gradle grun --name=runnerClassName --folderName=runnerspackage
```

```bash

┃ ┗ 📂test
┃   ┣ 📂java
┃   ┃ ┗ 📂co
┃   ┃   ┗ 📂com
┃   ┃     ┗ 📂bancolombia
┃   ┃       ┗ 📂certificacion
┃   ┃         ┗ 📂[principalPackage]
┃   ┃           ┣ 📂runners
┃   ┃           ┃ ┗ 📂[runnerspackage]
┃   ┃           ┃   ┗ 📜[name].java
```

# How do it?
<img src="./assets/Frame4GenerateRunners.gif" alt="Demostración del uso" width="80%"/>

## Generate Rest Interaction

The **`generateRestInteraction | gri`** task will generate the rest interaction classes, this task has one required
parameter `typeInteraction`

- **`typeInteraction`** `= interaction`: This parameter is going to specify the type of interaction to
  use. `field is required`
- **`nameInteraction`** `= Interaction`: This parameter is going to specify the name of interaction to
  use. `This field is required only when you choose` `GENERIC` `as type of interaction class`

```shell
  gradle generateRestInteraction --typeInteraction=[typeInteraction]
  gradle gri --typeInteraction=[typeInteraction] 
```

| Reference for **typeInteraction** | Name                |
|-----------------------------------|---------------------|
| Generic                           | Generic interaction |
| Post                              | Rest Post           |
| Get                               | Rest Get            |
| Options                           | Rest Options        |
| Patch                             | Rest Patch          |
| Put                               | Rest Put            |

```bash
 📦NameProject
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┗ 📂java
 ┃ ┃   ┗ 📂co
 ┃ ┃     ┗ 📂com
 ┃ ┃       ┗ 📂bancolombia
 ┃ ┃         ┗ 📂certificacion
 ┃ ┃           ┗ 📂[principalPackage]
 ┃ ┃             ┣ 📂exceptions
 ┃ ┃             ┣ 📂integrations
 ┃ ┃             ┣ 📂interactions
 ┃ ┃             ┃ ┣ 📜[Post].java
 ┃ ┃             ┃ ┣ 📜[Get].java
 ┃ ┃             ┃ ┣ 📜[Put].java
 ┃ ┃             ┃ ┣ 📜[Options].java  
 ┃ ┃             ┃ ┣ 📜[Patch].java  
 ┃ ┃             ┃ ┣ 📜[nameInteraction].java
```

# How do it?
<img src="./assets/Frame5GenerateInteraction.gif" alt="Demostración del uso" width="80%"/>

## Generate Tasks

The **`generateTask | gtk`** task will generate the Rest task or Ux class, this task has two required parameter `name`
and `typeTask`

- **`name`** `= nameTaskClass`: This parameter is going to specify the name task class to use. `field is required`
- **`typeTask`** `= Rest`: This parameter is going to specify the type task class to use. `field is required`
- **`method`** `= typeTask`: If you choose `Rest` for the `typeTask` field, the `method` field becomes mandatory, as it
  constructs the Task list for REST consumption. `field is required`

```shell
  gradle generateTask --name=[nameTaskClass] --typeTask=[typeTask] --method=[optionalField]
  gradle gtk --name=[nameTaskClass] --typeTask=[typeTask] --method=[optionalField]
```

| Reference for **typeTask** | Name      |
|----------------------------|-----------|
| Rest                       | Rest Rest |
| Ux                         | Rest Ux   |

```bash
 📦NameProject
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┗ 📂java
 ┃ ┃   ┗ 📂co
 ┃ ┃     ┗ 📂com
 ┃ ┃       ┗ 📂bancolombia
 ┃ ┃         ┗ 📂certificacion
 ┃ ┃           ┗ 📂[principalPackage]
 ┃ ┃             ┣ 📂exceptions
 ┃ ┃             ┣ 📂integrations
 ┃ ┃             ┣ 📂interactions
 ┃ ┃             ┣ 📂models
 ┃ ┃             ┣ 📂questions
 ┃ ┃             ┣ 📂tasks
 ┃ ┃             ┃ ┣ 📜[Rest].java
 ┃ ┃             ┃ ┣ 📜[Ux].java
```

# How do it?
<img src="./assets/Frame6GenerateTask.gif" alt="Demostración del uso" width="80%"/>

## Generate Pipeline

The **`generatePipeline | gpl`** task will generate CI/CD pipeline inside root project, this task has two required
parameters `name` and `type`

- **`name`** `= NamePipeline`: This parameter is going to specify the name of the pipeline. `field is required`
- **`type`** `= azure`: This parameter is going to specify the pipeline type. `field is required`

```shell
  gradle generatePipeline --name=pipelineCi --type=[type Pipeline] 
  gradle gpl --name=pipelineCi --type=[typePipeline] 
```

| Reference for **pipelineType** | Name           |
|--------------------------------|----------------|
| azure                          | Azure Pipeline |

```bash
   ┣ 📜build.gradle
   ┣ 📜[name]_Build.gradle
   ┗ 📜settings.gradle
```

# How do it?
<img src="./assets/Frame7GeneratePipeline.gif" alt="Demostración del uso" width="80%"/>

## Generate Database Connection

The **`dbConnection | dbc`** task will generate the class necessary to configure a connection to the databases
mentioned in the list attached to this description, this task has a required parameters `dataBase`

| Reference for **dataBase** |
|----------------------------|
| MySQL                      |
| PostgreSQL                 |
| Oracle                     |
| SQLServer                  |
| AS400                      |

- **`dataBase = MySQL`**: This parameter specify the database type what you need to use. `field is required`

```shell
  gradle dbConnection --dataBase=databasetype
  gradle dbc --dataBase=databasetype
```
# How do it?
<img src="./assets/Frame8GenerateDb.gif" alt="Demostración del uso" width="80%"/>

## Generate Critical Root

The **`generateCriticalRoot | gcr`** task will generate the Json file that containing the critical path feature,
this task has two required parameters `features` and `language`

- **`componentName`** `= component_name_test`: This parameter specify the project component name  `field is optional`
- **`features`** `= featureroot`: This parameter specify the feature root and name to extract the scenario separate for
  comma. `field is required`
- **`language`** `= languageFeature`: This parameter specifies the language used for Gherkin in the feature file. `field is required`

```shell
  gradle generateCriticalRoot --componentName=component_test --features=root/feature_one,root/feature_two --language=EN 
  gradle gcr --componentName=component_test --features=root/feature_one,root/feature_two --language=EN
```

| Reference for **language** | Name    |
|----------------------------|---------|
| EN                         | English |
| ES                         | Spanish |

# How do it?
<img src="./assets/Frame9GenerateRuta.gif" alt="Demostración del uso" width="80%"/>

# How can I help?

Review the issues, we hear new ideas. Read more [Contributing](https://github.com/bancolombia/)

# Whats Next?

Read more  [About ScreenPlay Architecure]()