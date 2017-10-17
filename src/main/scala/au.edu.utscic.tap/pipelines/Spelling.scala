/*
 * Copyright 2016-2017 original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.edu.utscic.tap.pipelines

import au.edu.utscic.tap.data.{TapSpell, TapSpelling}
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish
import org.languagetool.rules.RuleMatch

import scala.collection.JavaConverters._

/**
  * Created by andrew@andrewresearch.net on 17/10/17.
  */
object Spelling {



  val langTool = new JLanguageTool(new BritishEnglish)
  //TODO comment in to use statistical ngram data:
  //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));


  def check(text:String):Vector[TapSpell] = {
    val matches:Vector[RuleMatch] = langTool.check(text).asScala.toVector
    matches.map( m => TapSpell(m.getMessage,m.getSuggestedReplacements.asScala.toVector,m.getFromPos,m.getToPos))
  }

}