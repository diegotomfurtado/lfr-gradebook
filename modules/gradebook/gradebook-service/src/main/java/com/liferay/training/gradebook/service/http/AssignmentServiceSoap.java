/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.gradebook.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.training.gradebook.service.AssignmentServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * <code>AssignmentServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.training.gradebook.model.AssignmentSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.training.gradebook.model.Assignment</code>, that is translated to a
 * <code>com.liferay.training.gradebook.model.AssignmentSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentServiceHttp
 * @generated
 */
@ProviderType
public class AssignmentServiceSoap {

	public static com.liferay.training.gradebook.model.AssignmentSoap
			addAssignment(
				long groupId, String[] titleLanguageIds, String[] titleValues,
				String description, java.util.Date dueDate,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(
				titleLanguageIds, titleValues);

			com.liferay.training.gradebook.model.Assignment returnValue =
				AssignmentServiceUtil.addAssignment(
					groupId, title, description, dueDate, serviceContext);

			return com.liferay.training.gradebook.model.AssignmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.training.gradebook.model.AssignmentSoap
			deleteAssignment(long assignmentId)
		throws RemoteException {

		try {
			com.liferay.training.gradebook.model.Assignment returnValue =
				AssignmentServiceUtil.deleteAssignment(assignmentId);

			return com.liferay.training.gradebook.model.AssignmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.training.gradebook.model.AssignmentSoap
			getAssignment(long assignmentId)
		throws RemoteException {

		try {
			com.liferay.training.gradebook.model.Assignment returnValue =
				AssignmentServiceUtil.getAssignment(assignmentId);

			return com.liferay.training.gradebook.model.AssignmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.training.gradebook.model.AssignmentSoap
			updateAssignment(
				long assignmentId, String[] titleMapLanguageIds,
				String[] titleMapValues, String description,
				java.util.Date dueDate,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);

			com.liferay.training.gradebook.model.Assignment returnValue =
				AssignmentServiceUtil.updateAssignment(
					assignmentId, titleMap, description, dueDate,
					serviceContext);

			return com.liferay.training.gradebook.model.AssignmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AssignmentServiceSoap.class);

}