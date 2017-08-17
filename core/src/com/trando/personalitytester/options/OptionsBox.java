package com.trando.personalitytester.options;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.trando.personalitytester.traits.TraitHolder;
import com.trando.personalitytester.dialogue.DialogueBox;
import com.trando.personalitytester.utils.SoundManager;

/**
 * Created by Cameron on 3/3/2017.
 */
public class OptionsBox extends Table {
    private Array<Option> options;
    private Cell selectedCell;
    private DialogueBox dialogueBox;

    public OptionsBox(Skin skin, DialogueBox dialogueBox, Array<Option> options) {
        super(skin);
        this.options = options;
        this.dialogueBox = dialogueBox;

        if (options.size > 0) {
            this.setBackground("dialog.9");

            initAnswerLabels();
            selectedCell = this.getCells().first();
            highlightCell(selectedCell);
        }
    }

    /**
     * Wrapping text requires two things
     * Calling .setWrap(true) on your label
     * Putting the label in a parent with a fixed width
     */
    private void initAnswerLabels() {
        for (Option answer : options) {
            OptionLabel label = new OptionLabel(answer, getSkin());
            label.setWrap(true);

            this.add(label)
                .expand()
                .width(Gdx.graphics.getWidth() / 2)
                .right()
                .top()
                .pad(10f, 25f, 10f, 25f)
                .getActor().setColor(Color.GRAY);

            this.row();
        }
    }

    private void highlightCell(Cell cell) {
        cell.getActor().addAction(Actions.color(Color.WHITE));
    }

    private void unHighlightCell(Cell cell) {
        cell.getActor().clearActions();
        cell.getActor().setColor(Color.GRAY);
    }

    /**
     * Will highlight the cell above if the up key is pressed, will highlight below if the down key is pressed (will wrap)
     * Plays sounds on move
     * If enter is pressed, will add the label's stats to the Trait Holder if the dialogue has finished animating
     * @param keycode - The key being pressed
     */
    public void handleInput(int keycode) {
        switch (keycode) {
            case Input.Keys.DOWN:
                unHighlightCell(selectedCell);
                selectedCell = getCellBelow(selectedCell);
                highlightCell(selectedCell);
                SoundManager.playSound("click", 1.5f);
                break;
            case Input.Keys.UP:
                unHighlightCell(selectedCell);
                selectedCell = getCellAbove(selectedCell);
                highlightCell(selectedCell);
                SoundManager.playSound("click", 1.5f);
                break;
            case Input.Keys.ENTER:
                if(dialogueBox.isFinished()){
                    OptionLabel optionLabel = (OptionLabel) this
                            .getSelectedCell()
                            .getActor();
                    SoundManager.playSound("yes", 1.5f);
                    TraitHolder.incrementTrait(optionLabel.getTrait(), optionLabel.getValue());
                }
                break;
        }
    }

    /**
     * These method will only work when the table has one actor per row
     * Because of how Scene2D tables work, when there is only one actor per row, they are all
     * stored in one array
     *
     * @param cell the cell to analyze
     * @return the cell below it in a table
     */
    private Cell getCellBelow(Cell cell) {
        int row = cell.getRow();
        int rows = this.getRows();

        if (row < rows - 1) {
            return this.getCells().get(row + 1);
        } else return this.getCells().get(0);
    }

    private Cell getCellAbove(Cell cell) {
        int row = cell.getRow();
        int rows = this.getRows();

        if (row != 0) {
            return this.getCells().get(row - 1);
        } else return this.getCells().get(rows-1);
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }
}
