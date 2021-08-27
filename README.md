# Vanillify
Vanillify is a server-side library that allows fully-modded blocks on fabric servers, yet still allows vanilla minecraft clients to connect!

It does this by modifying the packets that are sent from the minecraft server to the minecraft client!
For example, you can make a block that teleports the player to a new dimension, but on the client the
block looks like a block of obsidian(or any block you specify!). This works for large amount of modded
items and blocks that utilize this library. It also supports chest-gui and block entities.

Supported Currently:

* **Items** - Completely modded items are supported.
* **Blocks** - Completely modded blocks are supported.
* **Block Entities** - Modded blocks WITH block entities are mostly supported.
* **Chest GUI** - Chest GUI are mostly supported.
* **Crafting Recipes** - Crafting recipes for the modded items and/or blocks are supported.
* **Entities** - Completely modded entities are supported.

## Developer Usage
This mod is actually a library and thus is mostly going to be used
by mod developers. You should know the following if you are a developer.
For more information please see the [Quick-Start Guide](https://github.com/BlazeCodeNet/VanillifyMod/wiki/Quick-Start-Guide)
on our wiki!

#### Key Terms
* **Representation** - A representation is a vanilla block or item(depending on use case) that will
be used to represent your modded block/item to vanilla clients!
* **Display Name** - A display name is the name that vanilla clients see when they hold the item.

## Project Goals
This project is in VERY early stages. Some things planned in the near future are:

* **Entity Support** - This would allow modded entities that look like vanilla entities to the client.
* **WTHIT-Mod Support** - Allowing support for the [WTHIT](https://github.com/badasintended/wthit) mod on clients with the mod installed is a key priority.
* **Pucking** - A term stolen from the [PolyPuck](https://github.com/TheEpicBlock/PolyPuck) project, it would allow vanilla clients to connect, but would also allow clients that connect WITH the client mod installed to see the modded things instead of their vanilla representations.  

## Known Bugs
Once again, this project is in VERY early stages. Please note that there ***will*** be bugs.
Some notable known issues are:
* **Creative Mode** - Creative mode causes SEVERE issues with modded items in particular.
  in practice it means that when a user with creative mode opens their creative inventory, it converts
  the modded item into its actual vanilla representational item. This will be extremely difficult to fix,
  so its going to be a bit off in the future in terms of fixes.
* **Block Breaking Speed** - If you have a different block breaking speed than your representative vanilla
  block, the client will appear to glitch when mining your modded block, sometimes horribly.
  We highly encurage you to find representative blocks that match your intended mining speed.
  
## Examples
You can view examples in the wiki [page](https://github.com/BlazeCodeNet/VanillifyMod/wiki/Mods-made-using-Vanillify) showcasing mods that use Vanillify.

## License
Vanillify is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/lucko/LuckPerms/blob/master/LICENSE.txt) for more info.
