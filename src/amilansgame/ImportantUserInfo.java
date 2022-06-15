/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amilansgame;

import basicgraphics.images.Picture;

/**
 *
 * @author mnguy
 */
public class ImportantUserInfo {

    String name;
    String avatarName;
    boolean foundCat;
    boolean foundDog;
    boolean foundFish;
    boolean talkedtoMilan;
    boolean bush1Searched;
    boolean bush2Searched;
    boolean foundPetList;
    boolean insideHouse;
    boolean outsideHouse;
    boolean inLSU;
    boolean lsuBush1Searched;
    boolean lsuBush2Searched;
    boolean inLockett;
    boolean foundSquirrel;
    boolean foundCockroach;
    boolean pondSearched;
    boolean foundRat;
    boolean plantSearched;
    boolean doorSearched;

///////////////////////LOCATION FLAGS
    public boolean isPlantSearched() {
	return plantSearched;
    }

    public void setPlantSearched(boolean plantSearched) {
	this.plantSearched = plantSearched;
    }

    public boolean isDoorSearched() {
	return doorSearched;
    }

    public void setDoorSearched(boolean doorSearched) {
	this.doorSearched = doorSearched;
    }

    public boolean isPondSearched() {
	return pondSearched;
    }

    public void setPondSearched(boolean pondSearched) {
	this.pondSearched = pondSearched;
    }

    public boolean isinLockett() {
	return inLockett;
    }

    public void setinLockett(boolean inLockett) {
	this.inLockett = inLockett;
    }

    public boolean islsuBush2Searched() {
	return lsuBush2Searched;
    }

    public void setlsuBush2Searched(boolean lsuBush2Searched) {
	this.lsuBush2Searched = lsuBush2Searched;
    }

    public boolean islsuBush1Searched() {
	return lsuBush1Searched;
    }

    public void setlsuBush1Searched(boolean lsuBush1Searched) {
	this.lsuBush1Searched = lsuBush1Searched;
    }

    public boolean isinLSU() {
	return inLSU;
    }

    public void setinLSU(boolean inLSU) {
	this.inLSU = inLSU;
    }

    public boolean isInsideHouse() {
	return insideHouse;
    }

    public void setInsideHouse(boolean insideHouse) {
	this.insideHouse = insideHouse;
    }

    public boolean isOutsideHouse() {
	return outsideHouse;
    }

    public void setOutsideHouse(boolean outsideHouse) {
	this.outsideHouse = outsideHouse;
    }

    public boolean isFoundPetList() {
	return foundPetList;
    }

    public void setFoundPetList(boolean foundPetList) {
	this.foundPetList = foundPetList;
    }

    public boolean isBush1Searched() {
	return bush1Searched;
    }

    public void setBush1Searched(boolean bush1Searched) {
	this.bush1Searched = bush1Searched;
    }

    public boolean isBush2Searched() {
	return bush2Searched;
    }

    public void setBush2Searched(boolean bush2Searched) {
	this.bush2Searched = bush2Searched;
    }

    public void setTalkedtoMilan(boolean talkedtoMilan) {
	this.talkedtoMilan = talkedtoMilan;
    }

    public boolean isTalkedtoMilan() {
	return talkedtoMilan;
    }
/////////////////////////////////////// PET FLAGS

    public boolean isFoundRat() {
	return foundRat;
    }

    public void setFoundRat(boolean foundRat) {
	this.foundRat = foundRat;
	theMain.animalStatus.get("rat").setText("rat");
	theMain.nameStatus.get("rat").setText("Stuart");
	theMain.saveCodeStatus.get("rat").setText("stuartlittle");
    }

    public boolean isFoundCockroach() {
	return foundCockroach;
    }

    public void setFoundCockroach(boolean foundCockroach) {
	this.foundCockroach = foundCockroach;
	theMain.animalStatus.get("cockroach").setText("cockroach");
	theMain.nameStatus.get("cockroach").setText("Michelle");
	theMain.saveCodeStatus.get("cockroach").setText("candybytwice");
    }

    public boolean isFoundSquirrel() {
	return foundSquirrel;
    }

    public void setFoundSquirrel(boolean foundSquirrel) {
	this.foundSquirrel = foundSquirrel;
	theMain.animalStatus.get("squirrel").setText("squirrel");
	theMain.nameStatus.get("squirrel").setText("Bill");
	theMain.saveCodeStatus.get("squirrel").setText("smoothieking");
    }

    public boolean isFoundCat() {
	return foundCat;
    }

    public void setFoundCat(boolean foundCat) {
	theMain.animalStatus.get("cat").setText("cat");
	theMain.nameStatus.get("cat").setText("Yoomie");
	theMain.saveCodeStatus.get("cat").setText("hungryhowies");
	this.foundCat = foundCat;
    }

    public boolean isFoundDog() {
	return foundDog;
    }

    public void setFoundDog(boolean foundDog) {
	this.foundDog = foundDog;
	theMain.animalStatus.get("dog").setText("dog");
	theMain.nameStatus.get("dog").setText("John");
	theMain.saveCodeStatus.get("dog").setText("thelionking");
    }

    public boolean isFoundFish() {
	return foundFish;
    }

    public void setFoundFish(boolean foundFish) {
	this.foundFish = foundFish;
	theMain.animalStatus.get("fish").setText("fish");
	theMain.nameStatus.get("fish").setText("Bubbles");
	theMain.saveCodeStatus.get("fish").setText("clashofclans");
    }
///////////////////////////////////////////OTHER THINGS

    public ImportantUserInfo() {
	name = "User";
	avatarName = "baldcharacter.png";
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
