#include <sgg/graphics.h>

#include "gamestate.h"

void draw()
{
    GameState::getInstance()->draw();
}

void update(float dt)
{
    GameState::getInstance()->update(dt);
}

int main(int argc, char** argv)
{
    graphics::createWindow(1000, 800, "Starland");
    graphics::setCanvasSize(GameState::getInstance()->getCanvasWidth(), GameState::getInstance()->getCanvasHeight());
    graphics::setCanvasScaleMode(graphics::CANVAS_SCALE_FIT);
    graphics::setDrawFunction(draw);
    graphics::setUpdateFunction(update);
    GameState::getInstance()->init();
    graphics::startMessageLoop();
	return 0;
}

