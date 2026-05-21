#pragma once
#include "gameobject.h"
#include "sgg/graphics.h"

class Enemy : public GameObject, public Collidable
{
	float speed;						//speed of the Enemy
	float size;							//size of the Enemy
	float rotation;						//rotetion of the Enemy
	graphics::Brush brush;				//brush to draw the Enemy
	bool active = true;					//boolean if Enemy is alive
public:
	float pos_x, pos_y;					//coordinates of the Enemy(x,y)
	void update(float dt) override;		// override from the upper class GameObject
	void draw() override;				// override from the upper class GameObject
	void init() override;				// override from the upper class GameObject
	bool isActive() { return active; }  //returns boolean if is alive or not
	Enemy();							//constructor
	~Enemy();							//destructor
	Disk getCollisionHull() const override; //returns an object of class Disk , useful for collision between the enemy and the player
											//struct Disk is in funcions.h
};
