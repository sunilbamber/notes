Differences between dependencyManagement and dependencies in Maven

URL: https://stackoverflow.com/questions/2619598/differences-between-dependencymanagement-and-dependencies-in-maven

Answer:

There's still one thing that is not highlighted enough, in my opinion, and that is unwanted inheritance.

Here's an incremental example:

I declare in my parent pom:

<dependencies>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>19.0</version>
        </dependency>
</dependencies>
boom! I have it in my Child A, Child B and Child C modules:

Implicitly inherited by child poms
A single place to manage
No need to redeclare anything in child poms
I can still redeclare and override to version 18.0 in a Child B if I want to.
But what if I end up not needing guava in Child C, and neither in the future Child D and Child E modules?

They will still inherit it and this is undesired! This is just like Java God Object code smell, where you inherit some useful bits from a class, and a tonne of unwanted stuff.

This is where <dependencyManagement> comes into play. When you add this to your parent pom, your child modules STOP seeing it. And Thus you are forced to go into each module that DOES need it and declare it again (Child A and Child B, without the version though).

And, obviously, you don't do it for Child C, and thus your module remains lean.