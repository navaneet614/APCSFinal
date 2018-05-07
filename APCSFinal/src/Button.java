import java.awt.Color;

import processing.core.PApplet;

public class Button {

	private float x, y, width, height;
	private String text;
	private Color edgeColor, fillColor, highlightFillColor, textColor, currentColor;

	public Button(float x, float y, float width, float height, String text, Color edgeColor, Color fillColor,
			Color highlightFillColor, Color textColor) {
		this(x, y, width, height, text);
		this.edgeColor = edgeColor;
		this.fillColor = fillColor;
		this.highlightFillColor = highlightFillColor;
		this.textColor = textColor;
	}

	public Button(float x, float y, float width, float height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		// Defaults
		this.edgeColor = Color.BLACK;
		this.fillColor = Color.WHITE;
		this.highlightFillColor = Color.LIGHT_GRAY;
		this.textColor = Color.BLUE;
	}

	public void draw(PApplet drawer) {
		drawer.rectMode(PApplet.CORNER);
		update(drawer.mouseX, drawer.mouseY);
		drawer.fill(currentColor.getRGB());

		drawer.stroke(edgeColor.getRGB());
		drawer.rect(x, y, width, height);

		drawer.fill(textColor.getRGB());
		drawer.textFont(drawer.createFont("Georgia", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text(text, x + width / 2, y + height / 2);
	}

	public boolean mouseOver(int mouseX, int mouseY) {
		return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
	}

	public String getText() {
		return text;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setHighlightColor(Color highlightColor) {
		this.highlightFillColor = highlightColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void update(int mouseX, int mouseY) {
		if (mouseOver(mouseX, mouseY)) {
			currentColor = highlightFillColor;
		} else {
			currentColor = fillColor;
		}
	}
}
