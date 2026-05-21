#pragma once
#include <string>
#include "gamestate.h"
#include "util.h"
#include "funcions.h"

//----------Collidable:The objects of this class is able to be collidable such as Player with Enemy,Player with diamond------------------------------ 
class Collidable {
public:
	virtual Disk getCollisionHull() const = 0;//this method will have definition in the subclassees
};

//-----------GameObject:almost everything in game is GameObject-------------------------------------
//-------contains methods which are necessary for the objects(draw,init,update)---------------------
class GameObject 
{
	static int m_next_id;
protected:
	class GameState* m_state;
	std::string m_name;
	int m_id = 0;
	bool m_active = true;

public:
	GameObject(const std::string& name = "");
	virtual void update(float dt) {}
	virtual void init() {}
	virtual void draw() {}
	virtual ~GameObject() {}
	bool isActive() { return m_active; }
	void setActive(bool a) { m_active = a; }
};
