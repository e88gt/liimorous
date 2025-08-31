package my.e88gt.liimorous.input;

import my.e88gt.liimorous.screen.*;

/**
 * input class for input events like
 * key presses, mouse clicks, etc,
 * to abuse polymorphism<br>
 * do 'if( input: {@link Input} instanceof some other input )'
 */
public interface Input
{
	Window getWindow();
}
