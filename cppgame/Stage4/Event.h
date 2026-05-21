#pragma once
#include"sgg/graphics.h"
#include"gameobject.h"


//---------Event:creates the dynamic events of the game---------------------
class Event
{
    protected:
        float m_pos_x;               //coorditates (x,y) of the Event 
        float m_pos_y;
        float m_duration = 1.0f;     //life's duration of the Event
        float m_delay = 0.0f;       //delay of the Event 
        float m_elapsed_time = 0.0f;
        float m_elapsed_delay = 0.0f;
        bool m_active = true;       //boolean if Event is alive
    public:
        const std::string m_asset_path = "assets\\"; //string to find the assets 
        virtual void draw();                         //method to draw the Event
        virtual  void update() ;                    //method to update the Event
        Event(float x = 0.0f, float y = 0.0f, float dur = 1.0f, float del = 2.0f);//constructor
        ~Event();//destructor
        bool active() { return m_active; }          //returns boolean if Event is alive
        void disable() { m_active = false; }        //method to kill/stop the Event
        bool waiting();                             //for some Events is useful ,is an extra status of a Event
};


//---------SmokeEvent is a subclass of Event-------------------------------------
class SmokeEvent : public Event
{
    protected:
        float m_orientation;                         //the SmokeEvent is going to be oriented
        float m_scale;                              //klimaka(step)
    public:
        GameState* m_state;                          //reference to GameState
        void draw() override;                        // override from the upper class GameObject
        SmokeEvent(float x, float y);               //constructor
        ~SmokeEvent();                              //destructor
};