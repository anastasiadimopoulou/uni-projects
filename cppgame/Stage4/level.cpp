#include "level.h"
#include <sgg/graphics.h>
#include "player.h"
#include "util.h"
#include <iostream>
#include <fstream>
#include <string>
#include "gamestate.h"
#include "Enemy.h"



float max_player_life = 100.0f;//max life of the player which will be decreased 

void Level::drawDoor(int i) //draw door 
{

	Box& box = m_doors[i];//all doors are inserted in a vector of boxes called m_doors
	std::string& name = m_door_names[i];//the names of the doors are inserted in a vector of strings called m_door_names
	float x = box.m_pos_x + m_state->m_global_offset_x;//box's x coordinate
	float y = box.m_pos_y + m_state->m_global_offset_y;//box's y coordinate
	m_door_brush.texture = m_state->getFullAssetPath(name);//get the path of the image that is used as a door
	m_door_brush.outline_opacity = 0.0f;//no outline
	graphics::drawRect(x, y, 1.0f, 1.0f, m_door_brush);//draw the door
	if (m_state->m_debugging)//if in debug mode 
		graphics::drawRect(x, y, 1.0f, 1.0f, m_door_brush_debug);//draw the door in debug mode @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}

void Level::drawBlock(int i) //draw blocks
{
	Box& box = m_blocks[i]; //all blocks are inserted in a vector of boxes called m_blocks
	std::string& name = m_block_names[i]; //the names of the blocks are inserted in a vector of strings calles m_block_names
	float x = box.m_pos_x + m_state->m_global_offset_x;//block's x coordinate
	float y = box.m_pos_y + m_state->m_global_offset_y;//block's y coordinate
	m_block_brush.texture = m_state->getFullAssetPath(name);//get the path of the image that is used as a block
	graphics::drawRect(x, y,  m_block_size,  m_block_size, m_block_brush);//draw the block
	if (m_state->m_debugging)//if in debug mode
		graphics::drawRect(x, y, m_block_size, m_block_size, m_block_brush_debug);//draw the block in debug mode @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}

void Level::drawDiamond(int i) //draw diamond
{
	Box* box = m_diamonds[i]; //all diamonds are inserted in a vector of boxes calles m_diamonds
	std::string& name = m_diamonds_names[i]; //the name of the diamonds 
	float x = box->m_pos_x + m_state->m_global_offset_x;// diamond's x coordinate
	float y = box->m_pos_y + m_state->m_global_offset_y;//diamond's y coordinate 
	m_block_brush.texture = m_state->getFullAssetPath(name);//get the path of the image that is used as a diamond
	graphics::drawRect(x, y, m_block_size, m_block_size, m_block_brush);//draw the diamond
	if (m_state->m_debugging)//if in debug mode
		graphics::drawRect(x, y, m_block_size, m_block_size, m_block_brush_debug); // draw the diamond in debug mode @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}




void Level::checkCollisions()//check the collisions
{
	for (auto& block : m_blocks) //loop for all the blocks that checks up and down intersections @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	{
		float offset = 0.0f;
			if (m_state->getPlayer()->m_vy > 0.0f) { // if the player goes up 
				if (offset = m_state->getPlayer()->intersectDown(block)) //check intersect down between the block and the player
				{
					m_state->getPlayer()->m_pos_y += offset; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					// add sound event
					if (m_state->getPlayer()->m_vy > 1.0f)
						graphics::playSound(m_state->getFullAssetPath("jump1.mp3"), 1.0f);
					m_state->getPlayer()->m_vy = 0.0f; //set velocity in axis y as zero
					break; //exit the loop
				}
			}
			if (m_state->getPlayer()->m_vy < 0.0f) { // if the player goes down
				if (offset = m_state->getPlayer()->intersectUp(block)) //check intersect up between the block and the player 
				{
					m_state->getPlayer()->m_pos_y += offset; //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					// add sound event
					if (m_state->getPlayer()->m_vy > 1.0f)
						graphics::playSound(m_state->getFullAssetPath("jump1.mp3"), 1.0f);
					m_state->getPlayer()->m_vy = 0.0f; //set velocity in axis y as zero
					break; //exit the loop 
				}
			}
	}
	for (auto& block : m_blocks) //loop for all the blocks that checks sideways intersetions
	{
			float offset = 0.0f;
			if (offset = m_state->getPlayer()->intersectSideways(block)) //check intersect sideways between the block and the player 
			{
				m_state->getPlayer()->m_pos_x += offset; //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				m_state->getPlayer()->m_vx = 0.0f; //set velocity in axis x as zero 
				break;
			}
	}
}


void Level::spawnMeteorite()//creates new enemies when the previous is off
{
	if (!meteorite)
	{
		meteorite = new Enemy();
	}
}

void Level::checkmeteorite()//when is not alive then delete it 
{
	if (meteorite && !meteorite->isActive())
	{
		delete meteorite;
		meteorite = nullptr;
	}
}



bool Level::checkCollision()//check collision between player and enemies
{
	if (!m_state->getPlayer() || !meteorite) //when there isn't either the player or the enemy(meteorite) then return false
	{
		return false;
	}
	Disk d1 = m_state->getPlayer()->getCollisionHull();//create the disks for the 2 objects(player and enemy)
	Disk d2 = meteorite->getCollisionHull();
	float dx = d1.cx - d2.cx;
	float dy = d1.cy - d2.cy;

	if (sqrt(dx * dx + dy * dy) < d1.radius + d2.radius)//if one disk is inside the other then 
	{
		addEvent(new SmokeEvent(meteorite->pos_x + 2.0f, meteorite->pos_y + 1.3f));//create the smoke event
		m_state->getPlayer()->drainLife(0.1f);//decrease the battery of the player
		return true;//there ia a collision
	}
	else
	{
		return false;//there is not a collision
	}
}

void Level::update(float dt)
{
	processEvents();//update the events in the list
	CollectDiamond();
	if (m_state->getPlayer()->isActive())
		m_state->getPlayer()->update(dt);
	checkmeteorite();//when meteorite (enemy) is not alive then delete it 
	spawnMeteorite();//whilw we have delete the previoue enemy we create the next enemy
	if (meteorite)
	{
		meteorite->update(dt);//if the enemy is alive then update it
	}
	if (checkCollision())//if there is collision between player and enemy then delete enemy(meteorite)
	{
		delete meteorite;
		meteorite = nullptr;
	}
	checkCollisions();//check collision with blocks
	GameObject::update(dt);
}

void Level::draw()
{
	float w = m_state->getCanvasWidth(); // width of the canvas
	float h = m_state->getCanvasHeight(); // height of the canvas
	float offset_x = m_state->m_global_offset_x / 2.0f + w / 2.0f; //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	float offset_y = m_state->m_global_offset_y / 2.0f + h / 2.0f; //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	graphics::drawRect(offset_x, offset_y, 4.0f * w, 8.0f * w, m_brush_background); //draw background
	
	if (m_state->getPlayer()->isActive()) { //if the player is active
		m_state->getPlayer()->draw(); //draw the player 
		//m_state->getPlayer()->moveSprites();
	}
	
	for (int i = 0; i < m_blocks.size(); i++) // for the number of blocks that are in the vector m_blocks
	{
		drawBlock(i); // draw blocks
	}
	
	for (int i = 0; i < m_doors.size(); i++) //for the number of doors that are in the vector m_doors
	{
		drawDoor(i);//draw door 
	}

	for (int i = 0; i < 3 ; i++) // for the 3 diamonds each level has
	{
		drawDiamond(i);//draw diamond as a box 
	}
	
	if (meteorite) // if the meteorite is alive 
	{
		meteorite->draw(); //draw the meteorite
	}
	
	for (auto ev : m_events) // fior all the events in the list
	{
		ev->draw(); //draw the event
	}
	//life battery
	graphics::Brush br_battery;
	float player_life = (m_state->getPlayer()->isActive()) ? m_state->getPlayer()->getRemainingLife() : 0.0f;//if player is alive then get the remaining life else return 0
	float battery_width = 2.0f; // width of the battery
	float battery_height = 1.0f; // height of the battery
	br_battery.outline_opacity = 0.0f;//no border
	br_battery.fill_color[0] = 0.2f;//colors
	br_battery.fill_color[1] = 0.2f;
	br_battery.fill_color[2] = 1.0f;
	br_battery.texture = " ";//no picture
	br_battery.fill_secondary_color[0] = 1.0f;//second color useful for gradient
	br_battery.fill_secondary_color[1] = 0.2f;
	br_battery.fill_secondary_color[2] = 0.2f;
	br_battery.gradient = true;
	br_battery.gradient_dir_u = 1.0f;
	br_battery.gradient_dir_v = 0.0f;
	//coordinates of the battery's border
	float battery_x = m_state->getCanvasWidth() - battery_width; 
	float battery_y = 1.0f; 
	graphics::drawRect(battery_x + (battery_width - player_life) - 1.0f, battery_y, player_life * battery_width, battery_height * 0.5f, br_battery);//life of the player which is decreased based of the player_life
	br_battery.outline_opacity = 1.0f;
	br_battery.gradient = false;
	br_battery.fill_opacity = 0.0f;
	graphics::drawRect(10.0f, 1.0f, 2.0f, 0.5f, br_battery);
	//diamond
	graphics::Brush br_diamond_icon;
	br_diamond_icon.outline_opacity = 0.0f;//no border
	br_diamond_icon.texture = m_state->getFullAssetPath("diamond.png"); // path to take the image
	graphics::drawRect(9.4f, 1.7f, 1.0f, 0.8f, br_diamond_icon); // position and size 
	br_diamond_icon.fill_opacity = 0.0f;
	// Draw the number of collected diamonds
	graphics::Brush br_diamond_count;
	graphics::drawText(10.0f, 2.0f, 1.0f, std::to_string(static_cast<int>(m_state->get_collected_diamonds())), br_diamond_count);//prints the counter of the collected_diamonds
	graphics::drawText(10.5f, 2.0f, 1.0f, "/", br_diamond_count);
	graphics::drawText(11.0f, 2.0f, 1.0f, "9", br_diamond_count);
}
void Level::init()
{
	// Stage 1
	for (auto p_gob : m_static_objects)
		if (p_gob) p_gob->init();
	for (auto p_gob : m_dynamic_objects)
		if (p_gob) p_gob->init();
	m_block_brush.outline_opacity = 0.0f; //no outline
	m_block_brush_debug.fill_opacity = 0.1f; // opacity 
	SETCOLOR(m_block_brush_debug.fill_color, 0.1f, 1.0f, 0.1f); //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	SETCOLOR(m_block_brush_debug.outline_color, 0.3f, 1.0f, 0.2f);//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}


void Level::LoadLevel(const std::string& filename) {

	std::ifstream inputFile(filename);

	// Check if the file is opened successfully
	if (!inputFile.is_open()) {//if the file is not opened successefully 
		std::cerr << "Unable to open the file." << std::endl; //print Unable to open
		std::cerr << "Error state: " << inputFile.rdstate() << std::endl; // print the state of the error
		return;
	}

	int crow = 0; //character's row 
	int ccol = 0; //character;s column
	int c = 0; // counter for the diamonds
	// Read and print the contents of the file
	char character;
	while (inputFile.get(character)) { //get the character 
		if (character == 'B') { //if the character is B
			m_blocks.push_back(Box(ccol, crow, 1, 1)); //insert in vector m_blocks a box based on the coordinates from txt file
			m_block_names.push_back("cube.png"); //insert in vector m_block_names the name of the image used as a block
			std::cout << character; //print the character 
		}
		else if (character == 'D') {//if the character is D
			m_doors.push_back(Box(ccol, crow, 1, 1));//insert in vector m_doors a box based on the coordinates from txt file
			m_door_names.push_back("door.png"); //insert in vector m_door_names the name of the image used as a block
			std::cout << character;//print the character 
		}
		else if (character == 'd') {//if the character is d
			std::cout << character; //print the character
			if (c == 0) { //haven't read a diamond from the txt file yet 
				int cold1 = ccol; //column of the 1st diamond
				int rowd1 = crow;// row of the 1st diamond 
				Box* dynamicdiamond1 = new Box(cold1, rowd1, 0.5, 0.5); //create the 1st dynamic diamond
				m_diamonds.push_back(dynamicdiamond1); //insert in vector m_diamonds the dynamicdiamond created above
				m_diamonds_names.push_back("diamond.png"); //insert in vector m_diamonds_names the name of the image used as a diamond
				c++;//1st diamond added 
			}
			else if (c == 1) { //have read one diamond from the txt file
				int cold2 = ccol;//column of the 2nd diamond
				int rowd2 = crow;// row of the 2nd diamond 
				Box* dynamicdiamond2 = new Box(cold2, rowd2, 0.5, 0.5); //create the 2nd dynamic diamond
				m_diamonds.push_back(dynamicdiamond2);//insert in vector m_diamonds the dynamicdiamond created above
				m_diamonds_names.push_back("diamond.png");//insert in vector m_diamonds_names the name of the image used as a diamond
				c++;//2nd diamond added
			}
			else if (c == 2) {//have read two diamonds from the txt file
				int cold3 = ccol;//column of the 3rd diamond
				int rowd3 = crow;// row of the 3rd diamond 
				Box* dynamicdiamond3 = new Box(cold3, rowd3, 0.5, 0.5);//create the 3rd dynamic diamond
				m_diamonds.push_back(dynamicdiamond3);//insert in vector m_diamonds the dynamicdiamond created above
				m_diamonds_names.push_back("diamond.png");//insert in vector m_diamonds_names the name of the image used as a diamond
				c++;//3rd diamond added
			}

		}

		ccol++;//next column
		if (ccol == 41) { // if 1st row has been read 
			crow++; //next row
			ccol = 0;//first column 
		}

	}
	// Close the file
	inputFile.close();
}


Level::Level(const std::string & name)
	: GameObject(name)
{
	m_brush_background.outline_opacity = 0.0f;
	m_brush_background.texture = m_state->getFullAssetPath("space_back.png");


}

Level::~Level()//destructor for the level
{
	for (auto p_go : m_static_objects)
		delete p_go; //delete all static objects
	for (auto p_go : m_dynamic_objects)
		delete p_go; //delete all dynamic objects
}

bool Level::isCompleted() //chacks if the level is completed
{
	Box door = m_doors[0]; //get the first of the doors (only one door in each level)
	bool doorcol = false; //variable becomes true when the player intesects with the door
	float offset = 0.0f;
	if (0 != m_state->getPlayer()->intersect(door)) { //checks intersection between the player and the door
		doorcol = true; 
		printf("#"); //print # in cmd
	}
	return doorcol; //return whether the player has completed the level or not 
}

void Level::CollectDiamond() //collect diamonds
{
	float offset = 0.0f;
	if (0 != m_state->getPlayer()->intersect(*m_diamonds[0])) { //if the player intersects with the 1st diamond
		printf("collect1"); //print collect1 (1st diamond is collected)
		delete  m_diamonds[0]; //delete the 1st diamond in the vector m_diamonds
		m_state->increase_collected_diamonds(); // increase the number of the diamonds collected

	}
	else if (0 != m_state->getPlayer()->intersect(*m_diamonds[1])) { //if the player intersects with the 2nd diamond
		printf("collect2");//print collect2 (2nd diamond is collected)
		delete   m_diamonds[1]; //delete the 2nd diamond in the vector m_diamonds 
		m_state->increase_collected_diamonds(); // increase the number of the diamonds collected
	}
	else if (0 != m_state->getPlayer()->intersect(*m_diamonds[2])) {//if the player intersects with the 3rd diamond
		printf("collect3");//print collect3 (3rd diamond is collected)
		delete   m_diamonds[2]; //delete the 3rd diamond in the vector m_diamonds
		m_state->increase_collected_diamonds();// increase the number of the diamonds collected
	}
}

string Level::getNextLevelPath(int currentLevel) {
	std::string basePath = "assets\\"; // Base path where your level files are stored
	std::string levelFileName;
	// Example logic: Sequential progression of level files named level1.txt, level2.txt, etc.
	levelFileName = "level" + std::to_string(currentLevel + 1) + ".txt";
	return basePath + levelFileName;
}

void Level::processEvents()
{
	for (auto e : m_events)
	{
		e->update();
	}
	m_events.remove_if([](Event* ev) {return !ev->active(); });
}

void Level::addEvent(Event* evt)
{
	m_events.push_front(evt);
}

