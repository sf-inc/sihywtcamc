# Versions Changelog

Currently, every **stable** mod version is available on **1.16.4** and **1.16.5** Minecraft versions.

* **X versions** are major updates with lots of new content. Very rare
* **x.X versions** are either updates that add content or major bug fixes
* **x.x.X versions** are either small content updates (language translation, new textures, ...) or bug fixes

## v1.4.3

* Port to 1.19.3
* Modified tools' attributes values can be modified in gui via mod menu

## v1.4.2

* Splash potions can be stacked by 16 (config)
* Lingering potions can be stacked by 16 (config)
* To balance stackable splash/lingering potions, cooldown to use them. Same for both 

## v1.4.1

* Adaptive invulnerability time: it depends on the used tool/weapon's attack speed. Resulting invulnerability 
  won't exceed vanilla one (20 ticks) but can be shorter
* Fixed compatibility issue for shield delay feature

## v1.4

* Now use the reach lib for better compatibility
* Shield blocks 100% of explosions damage if not caused by the player
* Shield blocks instantly, without any delay
* Rework tools/weapons config so you can put several ids with same attributes to write less lines: note that 
  you need to delete the old config file!

## v1.3.1

* Added configuration for flint that fires entities

## v1.3

* Stack count configurable
* Tool attributes fully configurable: only in the json (not in gui)
* Change modded tool attributes too! It means you also can add them a reach attribute
* Nerfed shield damage absorption: can absorb up to 5 damage, you take the remaining damage (config)

## v1.2

* Fixed reach distance computation: it seemed to be way too short? Now it's fixed and way better!
* The crosshair is now displaying accordingly to your reach distance
* Flint and steel on an entity sets it on fire for 3s and makes it target you

## v1.1.1

* Fixed bug added in previous version: enchantments were able to be multiple times on the same weapon (ex: 2 times power 3)

## v1.1

* Multi version support file: tested with 1.16.3 to 1.16.5. Should work for most of the versions
* Configurable mod: can enable/disable each feature separately
* Reduce egg/snow balls cooldown to 4 ticks: spam is back
* Fix the cancelling of eat/drink: now only when attacked (not cancelled for other damage sources)
* Reduce shield arc protection from 180 to 120 degrees
* Fix blocking no damage projectiles (eggs/snow balls): was able to block from any side
* Change tools/weapon attributes: attack damage and speed
* Add attack reach attributes to weapons

## v1.0.1

* Fix: server crash
* Fix: no features were enabled
* Multiversion support file: tested with 1.16.4 and 1.16.5

## v1.0

* Sword:
    * Sweeping edge active only with enchantment
* Shield:
  * Enchantable with the table: get unbreaking
  * Can add thorns and mending with anvil
* Axe:
  * Cooldown to use shield after attacking with axe
  * Cooldown to use axe after using shield
* Bow:
  * Power is less powerful, power 5 is the same as the old power 3
  * Either you get power to deal more damage, or you get punch/flame/infinity but not both
* Crossbow:
  * Multishot can be used with piercing
* Eat/Drink:
  * Eating/Drinking action is cancelled if attacked
  * Drink potions faster
* Stacks:
  * Non-throwable potions can be stacked by 16
  * Milk buckets can be stacked by 16
  * Snow balls can be stacked by 64
* Snow ball/egg:
  * If thrown by a player, hit and knockback players without damage
  * Knockback can be stopped with shield
