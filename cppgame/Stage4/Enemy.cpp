#include "Enemy.h"
#include "sgg/graphics.h"
#include <random>
#include "util.h"

void Enemy::update(float dt)
{
	pos_x = pos_x - speed * graphics::getDeltaTime();//update the x influenced by the deltatime
	rotation += 0.05f * graphics::getDeltaTime();
	if (pos_x < -size)//when enemy is out of the canvas do it false(kill it)
	{
		active = false;
	}
}

void Enemy::draw()
{
	graphics::setOrientation(rotation);//set orientation based on the rotation(rand)
	brush.texture = m_state->getFullAssetPath("moon1.png");//set the path to find the pictures
	graphics::drawRect(pos_x - 10, pos_y + 30, size, size, brush);//-10 to start outside of the canvas
	brush.texture = m_state->getFullAssetPath("moon2.png");//set the path to find the pictures
	graphics::drawRect(pos_x, pos_y, size, size, brush);
	graphics::resetPose();//resetPose at every moment

	/*	graphics::Brush br;
		br.outline_opacity = 1.0f;
		br.texture = "";
		br.fill_color[0] = 1.0f;
		br.fill_color[1] = 0.3f;
		br.fill_color[2] = 0.3f;
		br.fill_opacity = 0.3f;
		br.gradient = false;
		Disk hull = getCollisionHull();
		graphics::drawDisk(hull.cx, hull.cy, hull.radius, br);
	*/


}


void Enemy::init()
{
	speed = 0.008f;//speed of the enemy
	pos_x = 12.0f + 1.1f * size; 
	pos_y = 6.0f * (rand() / (float)RAND_MAX);//random y from 0 to 1*6(the height of the canvas)
	size = 0.5f + (rand() / (float)RAND_MAX);
	rotation = 360 * (rand() / (float)RAND_MAX);
	brush.outline_opacity = 0.0f; //border of the enemies

}

//constructor
Enemy::Enemy()
{
	init();
}


//destructor
Enemy::~Enemy()
{

}

//creates the disk (useful for debug) for an enemy
Disk Enemy::getCollisionHull() const
{
	Disk disk;
	disk.cx = pos_x; //x of the disk=x of the enemy
	disk.cy = pos_y; //y of the disk=y of the enemy
	disk.radius = size - 0.3f; //radius of the disk(aktina)
	return disk;
}
