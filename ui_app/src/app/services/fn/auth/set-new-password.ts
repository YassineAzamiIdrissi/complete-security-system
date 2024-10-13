/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NewPasswordCouple } from '../../models/new-password-couple';

export interface SetNewPassword$Params {
  code: string;
      body: NewPasswordCouple
}

export function setNewPassword(http: HttpClient, rootUrl: string, params: SetNewPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
  const rb = new RequestBuilder(rootUrl, setNewPassword.PATH, 'patch');
  if (params) {
    rb.query('code', params.code, {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<{
      }>;
    })
  );
}

setNewPassword.PATH = '/auth/set-new-password';
