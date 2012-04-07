package org.crsh.cmdline.matcher.impl;

import org.crsh.cmdline.ClassDescriptor;
import org.crsh.cmdline.MethodDescriptor;
import org.crsh.cmdline.matcher.CmdCompletionException;
import org.crsh.cmdline.matcher.tokenizer.Termination;
import org.crsh.cmdline.spi.CompletionResult;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 */
class MethodCompletion<T> extends Completion {

  /** . */
  private final ClassDescriptor<T> descriptor;

  /** . */
  private final String mainName;

  /** . */
  private final  String prefix;

  /** . */
  private final Termination termination;

  MethodCompletion(ClassDescriptor<T> descriptor, String mainName, String prefix, Termination termination) {
    this.descriptor = descriptor;
    this.mainName = mainName;
    this.prefix = prefix;
    this.termination = termination;
  }

  @Override
  protected CompletionResult<String> complete() throws CmdCompletionException {
    CompletionResult<String> completions = new CompletionResult<String>(prefix);
    for (MethodDescriptor<?> m : descriptor.getMethods()) {
      String name = m.getName();
      if (name.startsWith(prefix)) {
        if (!name.equals(mainName)) {
          completions.put(name.substring(prefix.length()), termination.getEnd());
        }
      }
    }
    return completions;
  }
}
