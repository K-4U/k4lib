K4Lib
===
---
K4Lib is a mod used by several of K4Unl's mods:

- [Hydraulicraft](http://www.github.com/K-4U/Hydraulicraft)
- [Colorchat](http://www.github.com/K-4U/colorchat)
- [PvpToggle](http://www.github.com/K-4U/PvpToggle)
- [DoubleJump](http://www.github.com/K-4U/DoubleJumper)


# License #
---
This mod is released under the MMPLv2

# Developing with this mod #
---
Mod devs: Using my mod is easier than ever thanks to maven:

	repositories {
	    maven {
	        name = "MM repo"
	        url = "http://maven.k-4u.nl/"
	    }
	}

	dependencies {
		compile "k4unl:k4lib:1.7.10-0.1.49:deobf"
	}

Or use the 1.8 version:

	repositories {
	    maven {
	        name = "MM repo"
	        url = "http://maven.k-4u.nl/"
	    }
	}

	dependencies {
		compile "k4unl:k4lib:1.8-1.0.9:deobf"
	}
