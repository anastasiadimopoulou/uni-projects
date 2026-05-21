#include "Event.h"
#include <random>
#include "util.h"
#include"gamestate.h"
#include "sgg/graphics.h"


void Event::update()
{
    if (!m_active)//if event is not alive
    {
        return;
    }
    if (waiting())//if event is waiting (m_elapsed_delay < m_delay)
    {
        m_elapsed_delay += graphics::getDeltaTime() / 1000.0f;
        return;
    }
    m_elapsed_time += graphics::getDeltaTime() / 1000.0f;
    if (m_elapsed_time > m_duration)
    {
        m_active = false;
    }
}

Event::Event(float x, float y, float dur, float del)//constructor
    :m_pos_x(x), m_pos_y(y), m_duration(dur), m_delay(del)
{

}

bool Event::waiting()
{
    return m_elapsed_delay < m_delay;
}

void Event::draw()
{
    graphics::Brush br;
    br.texture = m_asset_path + ("smoke.png");
    br.outline_opacity = 0.0f;//no border
    br.fill_opacity = 1.0f ;
    graphics::drawRect(m_pos_x - 3.0f, m_pos_y - 3.0f, 0.5f, 0.5f, br);
    graphics::resetPose();
}
//-------------------------------------------------------------------------------------
void SmokeEvent::draw()
{
    graphics::Brush br;
    float s = m_elapsed_time / m_duration;
    br.texture = m_asset_path + ("smoke.png");
    br.outline_opacity = 0.0f;//no border
    br.fill_opacity = 1.0f ;
    graphics::setScale(m_scale - s, m_scale - s);//to dissapear after some time
    graphics::setOrientation(m_orientation + s * 20.0f);//set orientation
    graphics::drawRect(m_pos_x - 3.0f, m_pos_y -1.0f, 2.0f, 2.0f, br);
    graphics::resetPose();
}

SmokeEvent::SmokeEvent(float x, float y)//constructor 
    :Event(x, y, 2.0f, 0.0f)
{
    m_orientation = rand() / (float)RAND_MAX * 180.0f - 90.0f;//update orientation
    m_scale = 0.8f + rand() / (float)RAND_MAX * 0.4f;//update scale 

}
