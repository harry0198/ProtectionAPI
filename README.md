# ProtectionAPI
MarkdownVersion - 0.0.1-PRE-RELEASE

ProtectionAPI is a Universal Protection Plugin and developer application interface. ProtectionAPI acts as a bridge between lots of plugins,
providing a singular, defined and clean cut solution to interacting with boundaries without firing false break events which can cause most plugins to break. 

## Contributing
The Most important part of this project is contributions! We **REQUIRE** people to include their own implementations to keep the
project up-to-date and working across a broad range of plugins. Obviously, one team cannot accomplish this.

If you wish to add your own solution to the project, you must open a pull request and add a new **module** to the project.
This project compiles all modules into an uber jar where your module can be interacted with other plugins. There is a tutorial
on how to include your module (todo insert whereever they go).

The process can be daunting at first but it's important you follow the guidelines.

## Using the API
TODO

## Versioning
As plugins update, protocols change and require updates to keep errors from occuring. Luckily, we have some protective measures in place to prevent this becoming
a server breaking condition. New releases will always be posted on the github releases section first. Unfortunately, we may have to remove some supported modules if
the author does not include valid API methods for this project to use or requires constant updates (note this is unlikely). 