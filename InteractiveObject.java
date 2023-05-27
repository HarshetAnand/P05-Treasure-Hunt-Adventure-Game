//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Treasure Hunt: InteractiveObject Class
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
 * This class models a clickable interactive object used in cs300 Spring 2022 p05 Treasure Hunt
 * application.
 */

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InteractiveObject implements Clickable {

  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is
  // clicked the first time, if any

  /**
   * Draws this interactive object (meaning drawing its image) to the display window at positions x
   * and y.
   */
  @Override
  public void draw() {
    // TODO Auto-generated method stub
    processing.image(this.image, this.x, this.y);
  }

  /**
   * This method operates each time the mouse is pressed. This interactive object responds to the
   * mouse clicks as follows. If the mouse is clicked (meaning the mouse is over it) two operations
   * will be performed as follows: (1) The message of this interactive object must be displayed to
   * the console. (2) If this interactive object has a next clue and was not clicked, its next clue
   * will be activated and its wasClicked setting will be updated.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver() == true && processing.mousePressed == true) {
      System.out.println(message());
    }
    if (this.wasClicked == false && (!(nextClue == null))) {
      this.wasClicked = true;
      activateNextClue();
    }
  }

  /**
   * This method operates each time the mouse is released. It implements a default behavior for an
   * interactive object which is DO NOTHING (meaning that the InteractiveObject.mouseReleased has a
   * blank implementation).
   */
  @Override
  public void mouseReleased() {
    // TODO Auto-generated method stub
  }

  /**
   * Checks whether the mouse is over the image of this interactive object.
   */
  @Override
  public boolean isMouseOver() {
    // TODO Auto-generated method stub
    if (processing.mouseX >= this.getX() && processing.mouseY >= this.getY()
        && processing.mouseX <= this.getX() + image.width
        && processing.mouseY <= this.getY() + image.height) {
      return true;
    }
    return false;
  }

  /**
   * This method creates an interactive object which x is the position of the x point and y is the
   * position of the y point and processes a message of the objects position.
   * 
   * @param name    represents the name of the interactive object
   * @param x       is position of x-coordinate
   * @param y       is position of y-coordinate
   * @param message of objects position
   */
  public InteractiveObject(String name, int x, int y, String message) {
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    this.wasClicked = false;
    this.isActive = true;
    this.nextClue = null;
    this.image = processing.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * This method creates an interactive object which x is the position of the x point and y is the
   * position of the y point and processes a message of the objects position and the next clue of
   * the object.
   * 
   * @param name     represents the name of the interactive object
   * @param x        is position of x-coordinate
   * @param y        is position of y-coordinate
   * @param message  of objects position
   * @param nextClue represents the next clue
   */
  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue) {
    this(name, x, y, message);
    this.wasClicked = false;
    this.isActive = true;
    this.nextClue = nextClue;
    this.image = processing.loadImage("images" + File.separator + name + ".png");
    this.nextClue.deactivate();
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects will
   * be drawn. Note that a graphic application has ONLY one display window of type PApplet where all
   * graphic objects interact. In p05, the TreasureHunt class is of type PApplet.
   * 
   * @param processing - represents the reference to the TreasureHunt PApplet object where all the
   *                   interactive objects will be drawn.
   */
  public static void setProcessing(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }

  /**
   * Gets the x-position of this interactive object.
   * 
   * @return the x-position of this interactive object
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-position of this interactive object.
   * 
   * @return the y-position of this interactive object
   */
  public int getY() {
    return this.y;
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively.
   * 
   * @param dx - move to be added to the x position of this interactive object
   * @param dy - move to be added to the y position of this interactive object
   */
  public void move(int dx, int dy) {
    this.x = this.x + dx;
    this.y = this.y + dy;
  }

  /**
   * Checks whether the name of this interactive object equals to the name passed as input
   * parameter. We consider a case-sensitive comparison.
   * 
   * @param name - name to compare to
   * @return - true if the name of this interactive object equals the provided name, false
   *         otherwise.
   */
  public boolean hasName(String name) {
    if (this.NAME.equals(name)) {
      return true;
    }
    return false;
  }

  /**
   * Checks whether this interactive object is active. This should be a getter of the isActive data
   * field.
   * 
   * @return true if this interactive object is active and false otherwise.
   */
  public boolean isActive() {
    if (this.isActive) {
      return true;
    }
    return false;
  }

  /**
   * Activates this interactive object, meaning setting its isActive data field to true.
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * Deactivates this interactive object, meaning setting its isActive data field to false.
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * Gets the message of this interactive object.
   * 
   * @return the message to be displayed each time this interactive object is clicked.
   */
  public String message() {
    return this.message;
  }

  /**
   * Sets the next clue associated to this interactive object.
   * 
   * @param nextClue - the next clue to be assigned to this interactive object
   */
  public void setNextClue(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt
   * application.
   * 
   * @throws NoSuchElementException - with a descriptive error message if the nextClue of this
   *                                interactive object is null
   */
  protected void activateNextClue() throws NoSuchElementException {
    if (!(this.nextClue != null)) {
      throw new NoSuchElementException("The next clue is null");
    }
    this.nextClue.isActive = true;
    processing.add(this.nextClue);
  }
}
