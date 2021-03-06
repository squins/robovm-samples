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
 * Portions of this code is based on Apple Inc's TableSearch sample (v1.2)
 * which is copyright (C) 2015 Apple Inc.
 */
package org.robovm.samples.tablesearch;

import java.util.ArrayList;
import java.util.List;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSCoder;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.samples.tablesearch.ui.APLMainTableViewController;

public class TableSearch extends UIApplicationDelegateAdapter {

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        // load our data source and hand it over to APLMainTableViewController
        List<APLProduct> productsList = new ArrayList<>();
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iPhone", 2007, 599.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iPod", 2001, 399.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iPod touch", 2007, 210.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iPad", 2010, 499.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iPad mini", 2012, 659.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "iMac", 1997, 1299.00));
        productsList.add(new APLProduct(APLProduct.getDeviceTypeTitle(), "Mac Pro", 2006, 2499.00));
        productsList.add(new APLProduct(APLProduct.getPortableTypeTitle(), "MacBook Air", 2008, 1799.00));
        productsList.add(new APLProduct(APLProduct.getPortableTypeTitle(), "MacBook Pro", 2006, 1499.00));

        UINavigationController navigationController = (UINavigationController) getWindow().getRootViewController();
        // note we want the first view controller (not the
        // visibleViewController) in case
        // we are being store from UIStateRestoration
        APLMainTableViewController viewController = (APLMainTableViewController) navigationController
                .getViewControllers().get(0);
        viewController.setProducts(productsList);

        return true;
    }

    @Override
    public boolean shouldSaveApplicationState(UIApplication application, NSCoder coder) {
        return true;
    }

    @Override
    public boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder) {
        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, TableSearch.class);
        }
    }
}
