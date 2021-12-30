import { Resolver } from '@parcel/plugin';
import logger from '@parcel/logger';
import path from 'path';


const getCljsResource = (opts) => {
  const {dependency, specifier, logger, options} = opts;
  // todo: assumes cljs code root is src/cljs 
  const match = specifier.match(/src\/cljs\/(.*).clj[sc]/);
  if(match) {
    const [_, cljsSourcePath] = match;
  
    // folders become namespaces
    const namespace = cljsSourcePath.replace('/', '.');
    const cljsTargetPath = 'target/';
    const filePath = path.resolve(options.projectRoot, `target/${namespace}.js`)
    
    logger.info({
      origin: 'parcel-resolver-cljs', 
      message: `replacing ${specifier} with ${filePath}`
    });

    return {
      filePath,
    };
  } else return null;
}

const getProjectResource = (opts) => {
  const {dependency, specifier, logger, options} = opts;
  const match = specifier.match(/^@project\/(.*)/);
  if(match) {
    const [_, newSpecifier] = match;
    if(newSpecifier) {
      // todo: configurable alias name?
      const filePath = path.resolve(options.projectRoot, newSpecifier);
      logger.info({
        origin: 'parcel-resolver-cljs', 
        message: `replacing ${specifier} with ${filePath}`
      });

      return {
        filePath,
      };
    }
  } else return null;
}

export default new Resolver({
  async resolve(opts) {
    const {dependency, specifier, logger, options} = opts;

    return (
      getCljsResource(opts) || 
      getProjectResource(opts)
    )
  }
});