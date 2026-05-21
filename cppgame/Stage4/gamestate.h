#pragma once
#include <string>

class GameState
{
	private:
		static GameState* m_unique_instance;
		const std::string m_asset_path = "assets\\";
		const float m_canvas_width  = 12.0f;
		const float m_canvas_height = 6.0f;
		int collected_diamonds = 0;//the amount of the diamonts which player has collect
		class Level * m_current_level = 0;
		class Player* m_player = 0;
	public:
		typedef enum{STATUS_START,STATUS_LEVEL1,STATUS_LEVEL2,STATUS_LEVEL3,STATUS_GAMEOVER} status_t;
		float f = 1.0f;
		float m_global_offset_x = 0.0f;
		float m_global_offset_y = 0.0f;
		int m_lvl_num ;
		bool m_debugging = false;
		status_t m_status=STATUS_START;
	public:
		GameState();
		~GameState();
		static GameState* getInstance();
		bool init();
		void draw();
		void update(float dt);
		std::string getFullAssetPath(const std::string& asset);
		std::string getAssetDir();
		float getCanvasWidth() { return m_canvas_width; }
		float getCanvasHeight() { return m_canvas_height; }
		void increase_collected_diamonds() { collected_diamonds++; }		//increase the counter of collected diamonds when the player collide with one of them
		int get_collected_diamonds() { return collected_diamonds; }			//returns the amount of the collected diamonds
		class Player* getPlayer() { return m_player; }
};
