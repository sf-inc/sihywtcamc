# Versions Changelog

Currently, every **stable** mod version is available on **1.16.4** and **1.16.5** Minecraft versions.

* **X versions** are major updates with lots of new content. Very rare
* **x.X versions** are either updates that add content or major bug fixes
* **x.x.X versions** are either small content updates (language translation, new textures, ...) or bug fixes

# v1.2

* Fixed reach distance computation: it seemed to be way too short? Now it's fixed and way better!
* The crosshair is now displaying accordingly to your reach distance
* Flint and steel on an entity sets it on fire for 3s and makes it target you

# v1.1.1

* Fixed bug added in previous version: enchantments were able to be multiple times on the same weapon (ex: 2 times power 3)

# v1.1

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
