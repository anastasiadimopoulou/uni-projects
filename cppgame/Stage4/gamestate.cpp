#include "gamestate.h"
#include "level.h"
#include "player.h"
#include <thread>
#include "SecondLevel.h"
#include <chrono>
#include "Enemy.h"

using namespace std::chrono_literals;

GameState::GameState()
{
}

GameState::~GameState()
{
	if (m_current_level)
		delete m_current_level;
}

GameState* GameState::getInstance()
{
	if (!m_unique_instance)
	{
		m_unique_instance = new GameState();
	}
	return m_unique_instance;
}

bool GameState::init()
{
	graphics::setFont(getFullAssetPath("orange juice 2.0.ttf"));
	graphics::preloadBitmaps(getAssetDir());
	return true;
}

void GameState::draw()
{
	graphics::Brush br;
	br.outline_opacity = 0.0f;
	br.texture = getFullAssetPath("space_back.png");
	graphics::Brush br_text;
	graphics::playMusic(getFullAssetPath("start.mp3"), 0.3f, false, 0);
	if (m_status == STATUS_START)
	{
		graphics::drawRect(getCanvasWidth() / 2.0f, getCanvasHeight() / 2.0f, getCanvasWidth(), getCanvasHeight(), br);
		br.texture = getFullAssetPath("character1.png");
		graphics::drawRect(getCanvasWidth() * 0.5f, getCanvasHeight() * 0.5f, 5.0f, 5.0f, br);
		graphics::drawText(getCanvasWidth() / 2 - 2, getCanvasHeight() / 2 + 3, 0.3f, "PRESS SPACE TO START", br_text);
	}
	else if (m_status == STATUS_LEVEL1) 
	{
		if (!m_current_level)
			return;
		m_current_level->draw();
	}
	else if (m_status == STATUS_LEVEL2)
	{
		if (!m_current_level)
			return;
		m_current_level->draw();
	}
	else if (m_status == STATUS_LEVEL3)
	{
		if (!m_current_level)
			return;
		m_current_level->draw();
	}
	else if (m_status == STATUS_GAMEOVER) 
	{
		graphics::drawRect(getCanvasWidth() / 2.0f, getCanvasHeight() / 2.0f, getCanvasWidth(), getCanvasHeight(), br);
		graphics::drawText(getCanvasWidth() / 2 - 2, getCanvasHeight() / 2 + 0.5f, 0.7f, "GAME OVER", br_text);
		graphics::playSound(getFullAssetPath("gameover.wav"), 0.3f);
	}
}

void GameState::update(float dt)
{
	// Skip an update if a long delay is detected 
	// to avoid messing up the collision simulation
	if (dt > 500) // ms
		return;

	// Avoid too quick updates
	float sleep_time = std::max(17.0f - dt, 0.0f);
	if (sleep_time > 0.0f)
	{
		std::this_thread::sleep_for(std::chrono::duration<float, std::milli>(sleep_time));
	}
	if (m_status == STATUS_START)//start window
	{
		if (graphics::getKeyState(graphics::SCANCODE_SPACE))//if space is pressed level1 is created
		{
			m_current_level = new Level("lvl1");//create level1 
			m_current_level->LoadLevel(getFullAssetPath("level1.txt"));//load level1 
			m_current_level->setName("level1");//set level's name as level1
			m_current_level->init();
			m_player = new Player("Player");//create the player
			m_player->init();
			m_status = STATUS_LEVEL1;
		}
	}
	else if (m_status == STATUS_LEVEL1)//level 1 
	{
		if (!m_current_level)
			return;
		m_current_level->update(dt);
		//jump to gameover if battery is<=0 
		if (f <= 0.0f) { m_status = STATUS_GAMEOVER; }
		m_debugging = graphics::getKeyState(graphics::SCANCODE_0);
		if (m_current_level->isCompleted())//if level1 is completed (player has reached the door of the 1st level)
		{
			m_current_level->~Level(); // Unload the current level if necessary
			m_current_level = new Level("lvl2");//create level2
			m_current_level->LoadLevel(getFullAssetPath("level2.txt"));//load level12
			m_current_level->setName("level2");//set level's name as level2
			m_current_level->init();
			m_player = new Player("Player2");//create the player
			m_player->init();
			m_status = STATUS_LEVEL2;
		}
	}
	else if (m_status == STATUS_LEVEL2)//level 2
	{
		if (!m_current_level)
			return;
		m_current_level->update(dt);
		if (f <= 0.0f) { m_status = STATUS_GAMEOVER; }//jump to gameover if battery is<=0 
		m_debugging = graphics::getKeyState(graphics::SCANCODE_0);
		if (m_current_level->isCompleted())//if level 2 is completed (player has reached the door of the 2nd level)
		{
			m_current_level->~Level(); // Unload the current level if necessary
			m_current_level = new Level("lvl3");//create level3
			m_current_level->LoadLevel(getFullAssetPath("level3.txt"));//load level3
			m_current_level->setName("level3");//set level's name as level3
			m_current_level->init();
			m_player = new Player("Player3");//create the player 
			m_player->init();
			m_status = STATUS_LEVEL3;
		}

	}
	else if (m_status == STATUS_LEVEL3)//level 3
	{
		if (!m_current_level)
			return;
		m_current_level->update(dt);
		
		if (f <= 0.0f) { m_status = STATUS_GAMEOVER; }//jump to gameover if battery is<=0 

		if (m_current_level->isCompleted())// if level 2 is completed (player has reached the door of the 3rd level)
		{
			m_current_level->~Level(); // Unload the current level if necessary
			m_status = STATUS_GAMEOVER; 
		}
		if (graphics::getKeyState(graphics::SCANCODE_ESCAPE)) { //if esc is pressed 
			delete m_current_level;//delete the level
			delete m_player;//delete the player
			m_status = STATUS_GAMEOVER;
		}
	}
	else if (m_status == STATUS_GAMEOVER)//game over window
	{
		if (graphics::getKeyState(graphics::SCANCODE_ESCAPE))//if esc is pressed close the window
		{
			m_status = STATUS_GAMEOVER;
		}

	}
}

std::string GameState::getFullAssetPath(const std::string& asset) //return the path 
{
	return m_asset_path + asset;
}

std::string GameState::getAssetDir()//return the directory
{
	return m_asset_path;
}

GameState* GameState::m_unique_instance = nullptr;
