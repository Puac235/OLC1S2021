import {Router} from 'express';

const router:Router = Router();

import {controller} from '../controllers/jpr.controller';

router.get('/',controller.helloWorld);

router.get('/interpretar',controller.helloWorld);

export default router;