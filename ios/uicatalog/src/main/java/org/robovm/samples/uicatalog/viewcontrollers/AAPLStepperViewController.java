/*
 * Copyright (C) 2013-2015 RoboVM AB
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 * Portions of this code is based on Apple Inc's UICatalog sample (v11.3)
 * which is copyright (C) 2008-2015 Apple Inc.
 */
package org.robovm.samples.uicatalog.viewcontrollers;

import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIStepper;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.IBOutlet;
import org.robovm.samples.uicatalog.Colors;

@CustomClass("AAPLStepperViewController")
public class AAPLStepperViewController extends UITableViewController implements UIControl.OnValueChangedListener {
    @IBOutlet
    private UIStepper defaultStepper;
    @IBOutlet
    private UIStepper tintedStepper;
    @IBOutlet
    private UIStepper customStepper;

    @IBOutlet
    private UILabel defaultStepperLabel;
    @IBOutlet
    private UILabel tintedStepperLabel;
    @IBOutlet
    private UILabel customStepperLabel;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        configureDefaultStepper();
        configureTintedStepper();
        configureCustomStepper();
    }

    private void configureDefaultStepper() {
        defaultStepper.setValue(0);
        defaultStepper.setMinimumValue(0);
        defaultStepper.setMaximumValue(10);
        defaultStepper.setStepValue(1);

        defaultStepperLabel.setText(String.valueOf((int) defaultStepper.getValue()));

        defaultStepper.addOnValueChangedListener(this);
    }

    private void configureTintedStepper() {
        tintedStepper.setTintColor(Colors.BLUE);

        tintedStepperLabel.setText(String.valueOf((int) tintedStepper.getValue()));

        tintedStepper.addOnValueChangedListener(this);
    }

    private void configureCustomStepper() {
        // Set the background image states.
        UIImage stepperBackgroundImage = UIImage.getImage("stepper_and_segment_background");
        customStepper.setBackgroundImage(stepperBackgroundImage, UIControlState.Normal);

        UIImage stepperHighlightedBackgroundImage = UIImage.getImage("stepper_and_segment_background_highlighted");
        customStepper.setBackgroundImage(stepperHighlightedBackgroundImage, UIControlState.Highlighted);

        UIImage stepperDisabledBackgroundImage = UIImage.getImage("stepper_and_segment_background_disabled");
        customStepper.setBackgroundImage(stepperDisabledBackgroundImage, UIControlState.Disabled);

        // Set the image which will be painted in between the two stepper
        // segments (depends on the states of both segments).
        customStepper.setDividerImage(UIImage.getImage("stepper_and_segment_divider"), UIControlState.Normal,
                UIControlState.Normal);

        // Set the image for the + button.
        UIImage stepperIncrementImage = UIImage.getImage("stepper_increment");
        customStepper.setIncrementImage(stepperIncrementImage, UIControlState.Normal);

        // Set the image for the - button.
        UIImage stepperDecrementImage = UIImage.getImage("stepper_decrement");
        customStepper.setDecrementImage(stepperDecrementImage, UIControlState.Normal);

        customStepperLabel.setText(String.valueOf((int) customStepper.getValue()));

        customStepper.addOnValueChangedListener(this);
    }

    @Override
    public void onValueChanged(UIControl control) {
        UIStepper stepper = (UIStepper) control;
        System.out.println(String.format("A stepper changed its value: %s.", control));

        // Figure out which stepper was selected and update its associated
        // label.
        UILabel stepperLabel = null;
        if (defaultStepper == stepper) {
            stepperLabel = defaultStepperLabel;
        } else if (tintedStepper == stepper) {
            stepperLabel = tintedStepperLabel;
        } else if (customStepper == stepper) {
            stepperLabel = customStepperLabel;
        }

        if (stepperLabel != null) {
            stepperLabel.setText(String.valueOf((int) stepper.getValue()));
        }
    }
}
