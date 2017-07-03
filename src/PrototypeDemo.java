/*
Class <code>Prototype</code> implements an example of the Structural Design Pattern Prototype pattern.
Please note that the comments in this file and the structure are not good java practices.
The usage of the java-sources are to demonstrate the Prototype pattern and not java coding.
Used are:
- Type ---- Name --------- Implements ---------
* Class     PrototypeDemo  Demo usage of the Prototype Pattern.
* Class     CloneFactory   Implements the generic getter for the clone.
* Interface Item           Forces the derived items to implements the getClone Method.
* Class     BasicItem      Abstract Class for methods that are valid for all derived Classes.
* Class     Book           Example implementation of an item implements Item extending BasicItem.
* Class     CS             Example implementation of an item implements Item extending BasicItem.
*
* The example uses a simplified (unrealistic) Book and CD as examples.
* The BasicItem abstract class has been introduced to show how a class:
* - Can extend a class or an abstract class
* - Can implement an interfacce
* For the protoype this is not a necessary extension.
*
* All classes are placed in this single file for simplicity and ease of usage.
*
* How useful is the prototype pattern using cloning in Java.
* Since nowadays the new operator is Java is extremely fast, cloning is not necessary anymore.
* So see the pattern implementation as an example of Java techniques for clone, interface and
* abstract classes.
*
* See the documentation on https://www.harmfrielink.nl/wiki/index.php/Prototype_(Pattern)
*/

import java.util.Hashtable;

/**
 * The calling Client-app class.
 * Uses 2 methods that are implemented:
 * - Sony or Philips implement the Prototype Interface.
 * - ConcreteRemote which extends (abstract) RemoteControl (which embeds the Brdige interface).
 */
public class PrototypeDemo {

   /**
    * Main method without any arguments.
    * @param args CLI Arguments (not-used).
    */
   static public void main(String[] args) {
      /* Gets an instance of CloneFactory. */
      CloneFactory itemMaker = new CloneFactory();

      /* Creating the initial instances. */
      Book theDaVinciCode = new Book() ; theDaVinciCode.setTitle("The Da Vinci Code");
      CD   pfEchoes       = new CD()   ; pfEchoes.setTitle("Echoes");

      /* Gets the cloned instances of the Book and CD. */
      Book clonedTheDaVinciCode = (Book) itemMaker.getClone(theDaVinciCode);
      CD   clonedEchoes         = (CD)   itemMaker.getClone(pfEchoes);

      /* Shows the object bucket numbers. */
      System.out.println("-------------------: Object Hash-codes (Bucket number)");
      System.out.println("Book Da Vinci Code : " + theDaVinciCode.hashCode());
      System.out.println("Clone Da Vinci Code: " + clonedTheDaVinciCode.hashCode());

      System.out.println("CD Echoes          : " + pfEchoes.hashCode());
      System.out.println("Clone Echoes       : " + clonedTheDaVinciCode.hashCode());

   } // main
}  // PrototypeDemo

/**
 * Class <code>CloneFactory</code> implements the cloning factory.
 */
class CloneFactory {

   /**
    * Gets the clone of an existing example.
    * @param  itemSample Instance of an Item (or a derived class).
    * @return clone of the existing sample instance.
    */
   public Item getClone(Item itemSample) {
      return itemSample.makeCopy();
   }  // Item
}  // class CloneFactory

/**
 * Interface <code>Item</code> implements the Prototype and has the clone method.
 */
interface Item extends Cloneable {

   /**
    * Makes a copy of the instance.
    * @return Item instance.
    */
   public Item makeCopy();

}  // interface Item

/**
 * Class <code>basicItem</code> implements the elements all items.
 */
abstract class BasicItem {
   /** Title for item.        */
   private String title = "";

   /** Proces for the item.   */
   private double price = 0.0;

    /**
    * Getter for the title.
    */
   public String getTitle() {
      return title;
   }  // getTitle

   public void setTitle(String title) {
      this.title = title;
   }  // setTitle

   /**
    * Getter for the price.
    */
   public double getPrice() {
      return price;
   }  // getPrice

   public void setPrice(Double price) {
      this.price = price;
   }  // setPrice
}  // class BasicItem

/**
 * Class <code>Book</code> extends the abstract class BasicItem implementing Item.
 * Implements prototype for Item Book.
 */
class Book extends BasicItem implements Item {
   private String ISBN;

   public Book() {
      System.out.println("Book has been created.");
   }

   @Override
   public Book makeCopy() {
      Book bookObject = null;

      try {
         bookObject = (Book) super.clone();
         System.out.println( String.format("Book '%s' has been cloned.", getTitle() ));
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      return bookObject;
   }

   public String toString(){
      return "Book XXX is my favorite";
   }
}  // Book

/**
 * Class <code>CD</code> extends the abstract class BasicItem implementing Item.
 * Implements prototype for Item CD.
 */
class CD extends BasicItem implements Item {
   private String CNNumber;

   public CD() {
      System.out.println("CD has been created.");
   }

   @Override
   public CD makeCopy() {
      CD cdObject = null;

      try {
         cdObject = (CD) super.clone();
         System.out.println( String.format("CD '%s' has been cloned.", getTitle() ));
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      return cdObject;
   }

   public String toString(){
      return "CD YYY is my favorite";
   }
}  // CD
