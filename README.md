# SoundProjector

**Requires Minecraft version: `1.12.2`**

A Minecraft mod designed to allow easier implementation of custom sound files into your Minecraft world. Compatible with survival multiplayer and essentially a direct copy of my Sound Projector block from [HEXCraft](https://www.curseforge.com/minecraft/mc-mods/hexcraft).

## Installation

1) Download the mod from CurseForge (link coming soon).
2) Place in `mods` folder.
3) Play!

## Development Setup

**For IntelliJ IDEA:**
1) Clone the repository locally.
2) Import the `build.gradle` into IntelliJ IDEA as a project.
3) Run in terminal: `gradlew setupDecompWorkspace`
4) Run in terminal: `gradlew setupDevWorkspace`
5) Run in terminal: `gradlew genIntellijRuns`
6) Go to "Edit Configurations" in top right.
7) Under "Use classpath of module", select the ".main" one.