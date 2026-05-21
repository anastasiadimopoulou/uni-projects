#pragma once
#include "gameobject.h"
#include <vector>
#include <list>
#include <string>
#include <sgg/graphics.h>
#include "player.h"
#include "level.h"
class SecondLevel : public Level ,public GameObject {

	

public:

	
	void update(float dt) override;
	void draw() override;
	void init() override;

	SecondLevel(const std::string& name = "Level2");
	~SecondLevel() override;



};