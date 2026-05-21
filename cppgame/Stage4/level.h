#pragma once
#include "gameobject.h"
#include <vector>
#include <list>
#include <string>
#include <sgg/graphics.h>
#include "player.h"
#include "Enemy.h"
#include "Event.h"

using namespace std;

class Level : public GameObject
{
	protected:
		graphics::Brush m_brush_background;
		std::vector<GameObject*> m_static_objects;
		std::list<GameObject*> m_dynamic_objects;
		
		Enemy* meteorite = nullptr;//reference to a Enemy 

		// add some collidable blocks
		std::vector<Box> m_blocks;//vector for blocks named m_blocks
		std::vector<std::string> m_block_names;//vector for names of the blocks named m_block_size
		const float m_block_size = 1.0f; //set size of the block 
		graphics::Brush m_block_brush; //brush for the block
		graphics::Brush m_block_brush_debug; //brush for the block in debug mode@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		//add door
		std::vector<Box> m_doors;//vector for doors named m_doors
		std::vector<std::string> m_door_names;//vector for names of the doors named m_door_size
		const float m_door_size = 1.0f;//set size of the door
		graphics::Brush m_door_brush;//brush for the door
		graphics::Brush m_door_brush_debug;//brush for the door in debug mode@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		//add diamonds
		std::vector<Box*> m_diamonds;//vector for diamonds named m_diamonds
		std::vector<std::string> m_diamonds_names;//vector for names of the diamonds named m_diamonds_size
		const float m_diamond_size = 0.5f;//set size of the diamond
		graphics::Brush m_diamonds_brush;//brush for the diamond
		graphics::Brush m_diamond_brush_debug;;//brush for the diamond in debug mode@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		float m_center_x = 5.0f;//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		float m_center_y = 5.0f;//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		// dedicated method to draw a block
		void drawBlock(int i);
		void drawDiamond(int i);

		//method to draw door 
		void drawDoor(int i);

		// detect collisions
		void checkCollisions();//check collision between  player and  blocks

		//create new enemies
		void spawnMeteorite();//method which create (new) Enemies
		void checkmeteorite();//delete a Enemy
		bool checkCollision();//check collision between player and Enemies
	public:
		std::list <Event*> m_events;//list of pointers in Event
		void update(float dt) override;
		void draw() override;
		void init() override;
		void LoadLevel(const std::string& filename);//load and read the level from txt file
		Level(const std::string & name = "Level0"); //constructor of level 0 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		~Level() override; //destructor for the level
		bool isCompleted();//checks if the level is completed
		void CollectDiamond();//collects the diamond
		string getNextLevelPath(int currentLevel);//returns the path of the next level
		std::string getName() {return m_name;} //return the level of the name @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		void setName(std::string  name) {  m_name=name; }//set the name of the level @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		void processEvents();		//update dynamic Events which are located to list <Event*> m_events
		void addEvent(Event* evt);  // add a object of Event to list <Event*> m_events
};