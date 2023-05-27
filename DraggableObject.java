//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Treasure Hunt: DraggableObject Class
// Course: CS 300 Spring 2022
//
// Author: Harshet Anand
// Email: hanand2@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Ahan Nair
// Partner Email: nair27@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a draggable object. A draggable object is a clickable interactive object which
 * can follow the mouse moves when being dragged.
 */

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DraggableObject extends InteractiveObject {
  private boolean isDragging;
  private int oldMouseX;
  private int oldMouseY;

  /**
   * Creates a new Draggable object with a given name, x and y position, and "Drag Me!" as a default
   * message.
   * 
   * @param name - name of object
   * @param x    - position (x-coordinate)
   * @param y    - position (y-coordinate)
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag Me!");
    this.isDragging = false;
  }

  /**
   * Creates a new Draggable object with a given name, x and y position, and a specific message.
   * 
   * @param name    - name of object
   * @param x       - position (x-coordinate)
   * @param y       - position (y-coordinate)
   * @param message - message corresponding to object's field
   */
  public DraggableObject(String name, int x, int y, String message) {
    super(name, x, y, message);
    this.isDragging = false;
  }

  /**
   * Checks whether this draggable object is being dragged.
   * 
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging() {
    if (!(this.isDragging)) {
      return false;
    }
    return true;
  }

  /**
   * Starts dragging this draggable object and updates the oldMouseX and oldMouseY positions to the
   * current position of the mouse.
   */
  public void startDragging() {
    this.isDragging = true;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Stops dragging this draggable object
   */
  public void stopDragging() {
    this.isDragging = false;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Draws this draggable object to the display window. If this object is dragging, this method sets
   * its position to follow the mouse moves. Then, it draws its image to the its current position.
   */
  @Override
  public void draw() {
    if (this.isDragging()) {
      this.move(processing.mouseX - this.getX(), processing.mouseY - this.getY());
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }
    processing.image(this.image, this.getX(), this.getY());
  }

  /**
   * Starts dragging this object when it is clicked (meaning when the mouse is over it)
   */
  @Override
  public void mousePressed() {
    if (super.isMouseOver() && processing.mousePressed == true) {
      this.startDragging();
    }
  }

  /**
   * Stops dragging this object if the mouse is released
   */
  @Override
  public void mouseReleased() {
    if (processing.mousePressed == false) {
      this.stopDragging();
    }
  }
}
