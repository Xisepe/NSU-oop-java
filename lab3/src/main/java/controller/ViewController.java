package controller;

import lombok.RequiredArgsConstructor;
import ui.FactoryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class ViewController implements ActionListener {
    private final FactoryView factoryView;

    @Override
    public void actionPerformed(ActionEvent e) {
        factoryView.update();
    }
}
