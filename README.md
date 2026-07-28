# [Tips++](https://github.com/oggvik/Tips)

Tips++ displays useful, configurable tips on Minecraft's loading and pause screens. Tips cycle automatically, and other mods and modpacks can add or remove tips.

## Fork status and attribution

Tips++ is an independently maintained fork of [Tips](https://github.com/Darkhax-Minecraft/Tips), originally created by Darkhax. The Tips++ fork is authored and maintained by **Oggvik**.

Darkhax and the original Tips project are not affiliated with, do not sponsor, and do not endorse Tips++. References to the original project are provided solely for attribution and to identify the upstream project.

See [FORK_NOTICE.md](FORK_NOTICE.md) for the permanent fork and licensing notice.

## Building

Tips++ targets Minecraft 1.16.5 with Forge and requires JDK 8 for its development environment.

```shell
./gradlew clean build
```

To launch the development client:

```shell
./gradlew 'Tips++Client'
```
