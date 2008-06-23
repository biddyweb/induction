/**
 *   Copyright 2008 Acciente, LLC
 *
 *   Acciente, LLC licenses this file to you under the
 *   Apache License, Version 2.0 (the "License"); you
 *   may not use this file except in compliance with the
 *   License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in
 *   writing, software distributed under the License is
 *   distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 *   OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing
 *   permissions and limitations under the License.
 */
package com.acciente.induction.controller;

/**
 * Used to instruct the client to redirect.
 *
 * Log
 * Jun 21, 2008 APR  -  created
 */
public class Redirect
{
   private  Class       _oControllerClass;
   private  String      _sControllerMethodName;
   private  String      _sURL;

   /**
    * A redirect object defined in terms of the controller to which the client should redirect,
    * Induction uses the redirect resolver to map the controller name to a URL.
    *
    * @param oControllerClass a class object representing a class that implements the Controller interface
    */
   public Redirect( Class oControllerClass )
   {
      _oControllerClass = oControllerClass;
   }

   /**
    * A redirect object defined in terms of the controller to which the client should redirect,
    * Induction uses the redirect resolver to map the controller name to a URL.
    *
    * @param oControllerClass a class representing that implements the Controller interface
    * @param sControllerMethodName a specific method name in the controller that the client
    * should redirect to
    */
   public Redirect( Class oControllerClass, String sControllerMethodName )
   {
      _oControllerClass       = oControllerClass;
      _sControllerMethodName  = sControllerMethodName;
   }

   /**
    * A redirect object defined directly in terms of the URL to which the client should
    * redirect to. The URL is still passed thru the redirect resolver which may choose
    * to complete partial URLs or provide other services.
    *
    * @param sURL a string representing a complete or partial URL
    */
   public Redirect( String sURL )
   {
      _sURL = sURL;
   }

   public Class getControllerClass()
   {
      return _oControllerClass;
   }

   public String getControllerMethodName()
   {
      return _sControllerMethodName;
   }

   public String getURL()
   {
      return _sURL;
   }
}

// EOF