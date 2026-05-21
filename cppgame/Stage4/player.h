#pragma once
#include"gamestate.h"
#include "gameobject.h"
#include <sgg/graphics.h>
#include "box.h"
#include"level.h"

class Player : public Box, public GameObject, public Collidable
{
		    // animated player
		std::vector<std::string> m_sprites;
		graphics::Brush m_brush_player;
		const float m_accel_horizontal = 50.0f;
		const float m_accel_vertical = 300.1f;
		const float m_max_velocity = 5.0f;
		const float m_gravity = 10.0f;
	public:
		float m_vx = 0.0f;
		float m_vy = 0.0f;
	public:
		void update(float dt) override;
		void draw() override;
		void init() override;
		Player(std::string name) : GameObject(name) {}
		Disk getCollisionHull() const override;//creates a disk as atool for debug and collision
		float getRemainingLife() { return m_state->f; }//returns the remaining life of the player during the collision with the enemies
		void drainLife(float amount) { m_state->f = std::max<float>(0.0f, m_state->f - amount); }//decrease the life of the player when the player collide with enemies
	protected:
		void debugDraw();
		// dynamic motion control
		void movePlayer(float dt);
};
