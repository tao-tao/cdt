/*******************************************************************************
 * Copyright (c) 2010, 2011 Andrew Gvozdev and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrew Gvozdev - Initial API and implementation
 *******************************************************************************/

package org.eclipse.cdt.internal.ui.language.settings.providers;

import java.net.URL;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import org.eclipse.cdt.core.language.settings.providers.ILanguageSettingsProvider;
import org.eclipse.cdt.core.language.settings.providers.LanguageSettingsManager;
import org.eclipse.cdt.core.language.settings.providers.LanguageSettingsSerializableProvider;
import org.eclipse.cdt.ui.CDTSharedImages;


/**
 * Label provider for language settings providers.
 *
 */
public class LanguageSettingsProvidersLabelProvider extends LabelProvider {
	private static final String TEST_PLUGIN_ID_PATTERN = "org.eclipse.cdt.*.tests.*"; //$NON-NLS-1$
	private static final String OOPS = "OOPS"; //$NON-NLS-1$

	/**
	 * Returns base image key (for image without overlay).
	 */
	protected String getBaseKey(ILanguageSettingsProvider provider) {
		String imageKey = null;
		// try id-association
		String id = provider.getId();
		URL url = LanguageSettingsProviderAssociationManager.getImageUrl(id);
		// try class-association
		if (url==null) {
			ILanguageSettingsProvider rawProvider = LanguageSettingsManager.getRawProvider(provider);
			if (rawProvider!=null) {
				url = LanguageSettingsProviderAssociationManager.getImage(rawProvider.getClass());
			}
		}
		if (url!=null) {
			imageKey = url.toString();
		}

		if (imageKey==null) {
			if (id.matches(TEST_PLUGIN_ID_PATTERN)) {
				imageKey = CDTSharedImages.IMG_OBJS_CDT_TESTING;
			} else {
				imageKey = CDTSharedImages.IMG_OBJS_EXTENSION;
			}
		}
		return imageKey;
	}

	/**
	 * Returns keys for image overlays. Returning {@code null} is not allowed.
	 */
	protected String[] getOverlayKeys(ILanguageSettingsProvider provider) {
		String[] overlayKeys = new String[5];

		if (isDebugging()) { // TODO temporary for debugging
			ILanguageSettingsProvider rawProvider = LanguageSettingsManager.getRawProvider(provider);
			if (rawProvider instanceof LanguageSettingsSerializableProvider) {
				if (((LanguageSettingsSerializableProvider)rawProvider).isEmpty()) {
					overlayKeys[IDecoration.BOTTOM_RIGHT] = CDTSharedImages.IMG_OVR_EMPTY;
				}
			}

			if (LanguageSettingsManager.isWorkspaceProvider(provider)) {
				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_GLOBAL;
//				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_REFERENCE;
//				overlayKeys[IDecoration.TOP_RIGHT] = CDTSharedImages.IMG_OVR_PARENT;
//				overlayKeys[IDecoration.BOTTOM_RIGHT] = CDTSharedImages.IMG_OVR_LINK;
			} else {
//				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_CONFIGURATION;
//				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_INDEXED;
//				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_CONTEXT;

//				overlayKeys[IDecoration.TOP_LEFT] = CDTSharedImages.IMG_OVR_PROJECT;
			}

		}
		return overlayKeys;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ILanguageSettingsProvider) {
			ILanguageSettingsProvider provider = (ILanguageSettingsProvider)element;
			String imageKey = getBaseKey(provider);
			String[] overlayKeys = getOverlayKeys(provider);
			return CDTSharedImages.getImageOverlaid(imageKey, overlayKeys);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ILanguageSettingsProvider) {
			ILanguageSettingsProvider provider = (ILanguageSettingsProvider) element;
			String name = provider.getName();
			if (name!=null) {
				if (LanguageSettingsManager.isWorkspaceProvider(provider)) {
					name = name + "   [ Shared ]";
				}
				return name;
			}
			String id = provider.getId();
			return "[ Not accessible id="+id+" ]";
		}
		return OOPS;
	}

	/**
	 * Temporary method for debugging only
	 */
	@Deprecated
	private boolean isDebugging() {
		return false;
//		return true;
	}
}