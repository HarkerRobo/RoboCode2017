/**
 * 
 */
package org.usfirst.frc.team1072.robot.smartDashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author joelmanning
 *
 */
public class SmartEnum<E extends Enum<?>> {
	
	private static int id = 0;
	
	private String name;
	private E init;
	
	public SmartEnum(E init){
		this(init, "SmartEnum" + id);
		id++;
	}
	
	public SmartEnum(E init, String name){
		this.name = name;
		this.init = init;
	}
	
	public E get(){
		try {
			Object choose = SmartDashboard.getData(name);
			if(choose == null){
				initializeChooser();
				return init;
			}
			if(choose instanceof SendableChooser<?>){
				return (E) ((SendableChooser) choose).getSelected();
			}
			initializeChooser();
			return init;
		} catch (IllegalArgumentException e){
			initializeChooser();
			return init;
		}
	}
	
	private void initializeChooser(){
		SendableChooser<E> choose = new SendableChooser<E>();
		E[] all = (E[]) init.getDeclaringClass().getEnumConstants();
		for(E e: all){
			choose.addObject(e.name(), e);
		}
		choose.addDefault(init.name(), init);
		SmartDashboard.putData(name, choose);
	}
}
