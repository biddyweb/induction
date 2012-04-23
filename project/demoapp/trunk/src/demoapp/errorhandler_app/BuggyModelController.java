/*
 * Copyright 2008-2012 Acciente, LLC
 *
 * Acciente, LLC licenses this file to you under the
 * Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */
package demoapp.errorhandler_app;

import com.acciente.induction.controller.Controller;
import com.acciente.induction.controller.Response;

import java.io.IOException;

/**
 * A controller that throws an error because it call a buggy model constructor
 *
 * @author Adinath Raveendra Raj
 * @created 4/23/12
 */
public class BuggyModelController implements Controller
{
   public void handler( Response oResponse, BuggyModel oBuggyModel ) throws IOException
   {
      oResponse.out().print( "Hello I am the BuggyModelController" );
   }
}
