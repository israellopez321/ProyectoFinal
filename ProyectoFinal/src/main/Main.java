package main;

import ui.MainMenu;
import mechanics.ItemRegistry;
import mechanics.SkillRegistry;

public class Main {
    public static void main(String[] args) {
    	
    	SkillRegistry.loadSkills();
    	ItemRegistry.loadItems();
    	
    	new MainMenu().display();
    }
    
}