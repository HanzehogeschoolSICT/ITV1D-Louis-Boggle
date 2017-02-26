package workers;

import controllers.BoardController;

import javax.swing.*;

public class DisplaySolveWorker extends SwingWorker<Void, Void> {
    private final BoardController boardController;
    private final JComponent[] components;

    /**
     * Initialize the display solve worker.
     *
     * @param boardController Board controller to use.
     * @param components Components to disable during the process.
     */
    public DisplaySolveWorker(BoardController boardController, JComponent... components) {
        this.boardController = boardController;
        this.components = components;
    }

    /**
     * Start solving the board.
     */
    public void start() {
        setComponentsState(false);
        execute();
    }

    /**
     * Solve the board in the background.
     *
     * @return Nothing.
     */
    @Override
    protected Void doInBackground() {
        boardController.solveBoard();
        return null;
    }

    /**
     * Enable the temporarily disabled controls.
     */
    @Override
    protected void done() {
        setComponentsState(true);
    }

    /**
     * Set the state of the given controls.
     *
     * @param enabled True to disable the controls, false otherwise.
     */
    private void setComponentsState(boolean enabled) {
        for (JComponent component : components)
            component.setEnabled(enabled);
    }
}
