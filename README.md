#Description
This project was originally a week long project designed to learn how to use **Scene2D**, a table layout based UI library built into **LibGDX**.

It it modeled after the introduction personality tester in Pokemon Mystery Dungeon - I actually did it it expecting to incorporate into my Pokemon Mystery Dungeon Clone, but it interestingly became its own app.

I returned to the project after several months after realizing that there were still portions of it left incomplete, such as the distorted background and responsive design. There are still a few parts that are unpolished, for example I could have it so that when the result is given, the program displays an image of the selected Pokemon. More questions still need to be added as well. 

#Features
* Easily addable questions and answers through editing JSON
* Automated parsing and conversion of JSON files into Question objects
* Table elements expand to allow for more options
* Can easily edit answers and their values, can even create new endings relatively easily
* Table elements resize on window resize - the shader distortion background has a size limit unfortunately, a result of LibGDX pixmaps not being properly converted to TextureRegions
* Can change the background shader by adding a new distortion map Texture and rebuilding the Texture Atlas
* Animated text and text wrapping

#Demo
Like most of my projects, a runnable jar can be found underneath the demo folder of the project. A small gif is included below.

![text](https://media.giphy.com/media/3oKGzeP6uq95liQ6pW/giphy.gif "Demo")

#Development Help
If you are kind enough to want to help with this project, after cloning it, here is what you can do.

* You can look at the questions.json file and start adding new questions, options, and answers. The format should be inside the JSON file.
* You can add more features to the program itself. The actual code base is relatively small, as I tried to keep everything nice and neat, so it should not be that hard to extend.

#Closing Words
This is actually my first complete project - I'm calling it complete. I hope it is not the last. I'm very happy and pleased with this experience, and maybe this project will inspire future me or someone else to just try their hand at making something small. It doesn't need to be the most useful or the most applicable, it just needs to be something fun. And this was a lot of fun!