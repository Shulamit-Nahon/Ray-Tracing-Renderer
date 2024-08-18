# Project Overview


This is a project of computer graphic, specifically a `Ray Tracing` engin.

This project is used to learn about some concepts and advanced topics in `Software Engineering`.

This project is the Practical course to the Intro to Software Engineering course
in this project we use java 17 to create and implement a raytracing engin
using some fundamental design pattern.

## Authors

- [@Shulamit](https://github.com/Shulamit-Nahon)
- [@Lea](https://github.com/leaHaimovich)


Software Engineering Concepts
---

- `TDD` ([Test-driven-development](https://en.wikipedia.org/wiki/Test-driven_development))
  in the project we learned how to use `unittesting` in order to check and balance our project.
- `RDD` ([Responsibility-driven design](https://en.wikipedia.org/wiki/Responsibilitydriven_design#:~:text=Responsibility%2Ddriven%20design%20is%20a,information%20that%20the%20object%20shares.))
  in this project we learned how we should choose who's responsible for what
  Examples from our project:
  1. in the picture improvement we chose that the function that will be used to generate `Points` for `Super Sampling` will be in the `Point.java` class
  2. in the picture improvement we chose that the function that will be used to generate `Vector` inside a`Cone` will be in the `Vector.java` class
  3. and so on...
- `Hard Code` we learned to not make our code rigid, for example using setter for some of our picture improvement so the user as the final say on if and how the effect will work.
- `Abstarction` or looking at some of the component of our code as `Black Box`, it is very useful in order to divide our goal into smaller achiveable tasks.
- `Law of Demeter` the law that stat that "Only talk to your immediate friends", was used in  every class in our project.
- `DRY` (Don't Repeat Yourself), don't copy code, write once use everywhere else.
- `KISS` (Keep it simple stupid) don't make your code overly complicated.

Design Patterns
---

here some of the pattern you can find in the project:

- `Builder` pattern in the `Scene.java` class
- `Composit` pattern in the `Geomtries.java` class
- `Wrapepr` pattern in the `Color.java` class
- 

and more!

3D Objects
---

here some of the 3D object supported in our project:

- Cylinder
- Tube
- Plane
- Polygon
- Triangle
- Sphere
- Cube (and all [Cuboid](https://en.wikipedia.org/wiki/Cuboid))

Lights
---

here's some fo the different light supported in our project:

- Ambient Light
- Directional Light
- Point Light
- Spotlight (with an option to set the beam angle)

Camera
---

our camera support:

- changing View Plane (width, height, distance)
- changing the view angle
  - rolling 
  - yawning
  - pitching
- camera movement
  - Up and Down (according to the camera up vector)
  - Forward and Backward (according to the camera to vector)
  - Right and Left (according to the camera right vector)
 
