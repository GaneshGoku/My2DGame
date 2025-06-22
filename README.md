
## 🧾 Abstract
"Boys Bizzar Adventure" is a 2D tile-based role-playing game (RPG) developed entirely in Java using the Swing framework. The project demonstrates the creation of a fully functional standalone game engine with features commonly found in commercial RPGs. It was designed with a focus on modular game architecture, real-time player interaction, and classic top-down adventure gameplay.

The game unfolds across multiple levels, including an overworld, trade shop, dungeon, and a final boss arena — each constructed with unique tilemaps and gameplay mechanics. Players navigate the world using keyboard controls to engage in combat, interact with NPCs, solve puzzles, trade items, and manage an in-game inventory system.

Core systems implemented include:

A Pathfinding* for intelligent enemy and NPC movement

Inventory and Item Trading mechanics

Real-time Combat System with animation triggers

Dialogue System for NPC interactions and quest progression

Level Transitioning between distinct maps with custom logic

Audio Management for background music and event-driven sound effects

The visual representation is built using a custom tile engine, with tiles and sprites rendered in layers to support collision detection, destructible objects, and environment interaction. The game's assets — including art, sound, and tiles — are sourced from various public and open-license repositories, tailored to match the retro adventure aesthetic.

"Boys Bizzar Adventure" serves as a showcase of object-oriented game development, event handling in Swing, and 2D world design in Java, all while delivering an engaging and immersive gameplay experience.
## 🎮 Boys Bizzar Adventure

> A handcrafted 2D Java RPG built with Swing — featuring puzzles, combat, trading, AI, and classic retro vibes!

**Boys Bizzar Adventure** is a full-featured 2D RPG written from scratch in Java. It combines tile-based world rendering, dynamic NPC interaction, quests, item trading, puzzles, and a multi-level adventure — all powered by a custom game engine using Java Swing. This is a solo indie project designed for learning, fun, and old-school game design!

---

## ✨ Features

- ⚔️ **Combat system** – Real-time combat triggered by keypress with enemy AI and health mechanics.
- 💼 **Inventory system** – Collect, use, and trade items with a clean UI panel.
- 🧠 **A* Pathfinding AI** – Enemies dynamically navigate the tilemap intelligently using A* algorithm.
- 🧙 **NPCs and Quests** – Talk to NPCs, receive quests, and track your story progression.
- 🧩 **Puzzles & Key Unlocks** – Solve puzzles and find keys to advance through dungeons.
- 🛒 **Item Trading** – Enter a trade shop to buy, sell, or barter items.
- 🌍 **Multiple Levels** – Explore different worlds, including:
  - **Level 0**: Overworld (Main game world)
  - **Level 1**: Trade Shop (Item buying/selling)
  - **Level 2**: Dungeon (Puzzle & enemy-filled area)
  - **Level 3**: Final Boss Battle (Epic showdown)
- 🔊 **Sound Effects & Music** – Action-based SFX and looping background music.
- 💬 **Dialogue System** – Dialog boxes with NPC conversations, quest text, and cutscene-style sequences.
- 🗺️ **Tilemap Rendering** – Fully tile-based world with custom collision and interaction layers.
- 🛠️ **God Mode** – Toggle for invincibility/debugging.

---

## 🕹️ Controls

| Action                  | Key        |
|-------------------------|------------|
| Move Up                 | `W`        |
| Move Down               | `S`        |
| Move Left               | `A`        |
| Move Right              | `D`        |
| **Attack**              | `ENTER`    |
| **Interact / Talk**     | `E`        |
| **Open Inventory**      | `C`        |
| **Open Map**            | `M`        |
| **Toggle God Mode**     | `G`        |
| **Pause / Settings**    | `ESC`      |

---

## 🚀 Getting Started

### ✅ Requirements

- Java JDK 8 or higher
- A Java IDE (e.g. IntelliJ IDEA, Eclipse, NetBeans)

### 💻 How to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/boys-bizzar-adventure.git
   cd boys-bizzar-adventure
   ```
## 🧠 Project Architecture
 ```bash
📁 src/
├── Data/            
├── Entity/              
├── ai/         
├── environment/         
├── main/            
├── mnster/  
├── object/          
├── tile/          
├── tile_interactive/             
📁 res/
├── contains all the asstes      
```


🛠 Core Systems Explained

🎮 Game Engine

Built using Java Swing + Java2D

Custom game loop with FPS control

GamePanel.java manages rendering, updating, and event processing.

🧠 Pathfinding & AI

Implemented using the A algorithm* in PathFinder.java

Enemies use G/H/F cost to track optimal route toward the player.

Avoids obstacles and interacts with collision tiles.

📦 Inventory & Items

Pick up weapons, potions, quest items, etc.

Items are displayed in an inventory panel triggered via C.

Items can be used, traded, or equipped.

🗣️ NPCs & Dialogue

NPCs show dialogue via textbox (e.g., quest prompts).

Some NPCs trade items; others offer hints or lore.

Interact using E.

🧩 Levels & Puzzles

Keys unlock doors, puzzles open secret areas.

Switches and logic gates handled with interactive tiles.

Each map is defined via a tileM.mapTileNum[][][] grid.

🔊 Audio System

Sound effects for attack, pickup, collision, etc.

Background music loops per level (using AudioInputStream).

All sounds triggered conditionally (e.g., item pickup, combat).

🐞 Known Issues

Occasional minor bugs (e.g., NPC overlap, rare map glitches)

Inventory stacking logic needs refinement

Some map transitions are not smooth

Undergoing active debugging and fixes

✅ Your feedback and pull requests are welcome!
## 🤝 Contributing
Want to contribute?

Fork this repo

Create a feature branch: git checkout -b new-feature

Commit your changes: git commit -m 'Added a feature'

Push to the branch: git push origin new-feature

Open a Pull Request 🎉




## video

(https://youtu.be/Nnh9L-de3po?si=wXdNfwSbUrEncPuE)
final vedio:-(https://youtu.be/3wz4Uw4AB0w)

