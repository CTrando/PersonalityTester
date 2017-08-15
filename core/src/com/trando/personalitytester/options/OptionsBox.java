package com.trando.personalitytester.options;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.trando.personalitytester.traits.TraitHolder;
import com.trando.personalitytester.dialogue.DialogueBox;

/**
 * Created by Cameron on 3/3/2017.
 */
public class OptionsBox extends Table {
    Array<Option> options;
    Cell selectedCell;
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
                .row();
        }
    }

    private void highlightCell(Cell cell) {
        cell.getActor().addAction(Actions.color(Color.GRAY));
    }

    private void unHighlightCell(Cell cell) {
        cell.getActor().clearActions();
        cell.getActor().setColor(Color.WHITE);
    }

    public void handleInput(int keycode) {
        switch (keycode) {
            case Input.Keys.DOWN:
                unHighlightCell(selectedCell);
                selectedCell = getCellBelow(selectedCell);
                highlightCell(selectedCell);
                break;
            case Input.Keys.UP:
                unHighlightCell(selectedCell);
                selectedCell = getCellAbove(selectedCell);
                highlightCell(selectedCell);
                break;
            case Input.Keys.ENTER:
                if(dialogueBox.isFinished()){
                    OptionLabel optionLabel = (OptionLabel) this
                            .getSelectedCell()
                            .getActor();
                    TraitHolder.incrementTrait(optionLabel.getTrait(), optionLabel.getValue());
                }
                break;
        }
    }

    /**
     * These method will only work when the table has one actor per row
     *
     * @param cell the cell to analyze
     * @return the cell below it in a table
     */
    private Cell getCellBelow(Cell cell) {
        int row = cell.getRow();
        int rows = this.getRows();

        if (row < rows - 1) {
            return this.getCells().get(row + 1);
        } else return cell;
    }

    private Cell getCellAbove(Cell cell) {
        int row = cell.getRow();
        int rows = this.getRows();

        if (row != 0) {
            return this.getCells().get(row - 1);
        } else return cell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }
}