package com.lazylearn.api.learnprogram;

import com.lazylearn.api.config.Consts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(Consts.STEP_PROGRAM__PIMSLEUR)
public class PimsleurProgram extends StaticLearnProgram{

}
