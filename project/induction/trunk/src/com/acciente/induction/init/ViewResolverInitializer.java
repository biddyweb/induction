/**
 *   Copyright 2009 Acciente, LLC
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
package com.acciente.induction.init;

import com.acciente.commons.reflect.ParameterProviderException;
import com.acciente.induction.dispatcher.model.ModelPool;
import com.acciente.induction.init.config.Config;
import com.acciente.induction.resolver.ViewResolver;
import com.acciente.induction.resolver.FullyQualifiedClassnameViewResolver;
import com.acciente.induction.util.ConstructorNotFoundException;
import com.acciente.induction.util.ObjectFactory;

import javax.servlet.ServletConfig;
import java.lang.reflect.InvocationTargetException;

/**
 * Internal.
 * This is helper class that focuses on setting up the view resolver used
 * by the dispatcher servlet.
 *
 * @created Mar 30, 2009
 *
 * @author Adinath Raveendra Raj
 */
public class ViewResolverInitializer
{
   public static ViewResolver getViewResolver( Config.ViewResolver   oViewResolverConfig,
                                               Config.ViewMapping    oViewMappingConfig,
                                               ModelPool             oModelPool,
                                               ClassLoader           oClassLoader,
                                               ServletConfig         oServletConfig,
                                               Logger                oLogger )
      throws ClassNotFoundException, InvocationTargetException, ConstructorNotFoundException, ParameterProviderException, IllegalAccessException, InstantiationException
   {
      ViewResolver   oViewResolver;
      String         sViewResolverClassName = oViewResolverConfig.getClassName();

      if ( sViewResolverClassName == null )
      {
         oViewResolver = new FullyQualifiedClassnameViewResolver( oViewMappingConfig );
      }
      else
      {
         oLogger.log( "loading user-defined view resolver: " + sViewResolverClassName );

         Class    oViewResolverClass  = oClassLoader.loadClass( sViewResolverClassName );

         // attempt to find and call the single public constructor
         oViewResolver
            =  ( ViewResolver )
               ObjectFactory.createObject( oViewResolverClass,
                                           new Object[]{ oServletConfig,
                                                         oViewResolverConfig,
                                                         oViewMappingConfig },
                                           new InitializerParameterProvider( oModelPool, "view-resolver-init" ) );
      }

      return oViewResolver;
   }
}