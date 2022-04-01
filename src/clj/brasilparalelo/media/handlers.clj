(ns brasilparalelo.media.handlers
  (:require
    [brasilparalelo.config :refer [env]]
    [brasilparalelo.db.core :as db]
    [brasilparalelo.media.predicates :as predicates]
    [buddy.auth :refer [authenticated?]]
    [clj-time.core :as time]
    [clojure.string :as string]
    [ring.util.http-response :refer :all]))

;(defn check-enrollment-period
;  [subscription]
;  (fn [media]
;    (let [{started_at :created_at} subscription
;          {released_at :released_at} media
;          finished_at (time/plus created_at (time/in-months 12))]
;      (if (time/before released_at started_at)
;        false
;        (time/before released_at finished_at)))))

;(defn foo
;  [{created_at created_at: type :type}]
;
;  {321123
;   :basic ["series"]
;   :patriot ["debates", "interviews" "podcast"]
;   :premium ["debates", "interviews", "report"]
;
;   }
;
;  )

;(defn- check-released-during-subscription
;  [{{released_at :released_at} :media {created_at :created_at} :subscription}]
;   media)

;(defn- check-enrollment-period
;  [{released_at :released_at} :media {created_at :created_at} :subscription]
;
;  (let [started_at created_at
;        finished_at (time/plus created_at (time/in-months 12))]
;    (if (time/before released_at started_at)
;      false
;      (time/before released_at finished_at))))

(defn create-all
  [{body :body}]
  {:status 201 :body body})

(defn create-one
  [{body :body}]
  {:status 201 :body body})

(defn find-patriot
  [request])

(defn find-premium
  [request])

(defn find-mecenas
  [request])

(def x

  [{
   :id "9afa0da2-d50e-4bb2-ae0d-04eebf0429db",
   :name "Há regras para educar os filhos?",
   :released_at "2020-03-29T20:02:34",
   :type "debate"
   },
  {
   :id "51e7ae00-777c-4381-8cd4-0a0967e75e54",
   :name "Introdução à Política Internacional",
   :released_at "2019-07-05T13:23:25",
   :type "course"
   },
  {
   :id "4a3dc8f5-6adf-4124-8351-0c5da5caab8c",
   :name "Uma Breve História da Rússia",
   :released_at "2019-06-18T12:29:21",
   :type "course"
   },
  {
   :id "f56dbf3e-0b01-4e03-8d08-0d1d91b5efa2",
   :name "Relatório Mecenas",
   :released_at "2020-08-10T20:00:00",
   :type "patron"
   },
  {
   :id "deae82aa-66e0-406b-8e29-0fb2f770d1b9",
   :name "Congresso Brasil Paralelo - Beatriz Kicis",
   :released_at "2018-08-16T20:14:20",
   :type "interview"
   },
  {
   :id "f0e08939-fed4-488d-9d3d-176096e4fe4b",
   :name "Congresso Brasil Paralelo - Cláudio Manoel",
   :released_at "2018-08-16T20:10:55",
   :type "interview"
   },
  {
   :id "da98c658-2b78-4e49-88f7-192256c29fe6",
   :name "Congresso Brasil Paralelo - Adriano Gianturco",
   :released_at "2019-11-16T21:40:51",
   :type "interview"
   },
  {
   :id "62edc726-a9e9-4a20-94dd-19829f18d50f",
   :name "História da Música",
   :released_at "2019-12-05T22:17:04",
   :type "course"
   },
  {
   :id "78e34217-8be2-46f0-b4b8-1f654fcdd591",
   :name "CORINGA - Podcast Cultura Paralela #1",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "a846afb8-0e61-4968-bd59-27fe1342ca75",
   :name "1917 - Podcast Cultura Paralela #8",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "65b79bfd-2035-4789-9008-281a6c4fdd06",
   :name "Fé e Liberdade",
   :released_at "2020-02-06T22:58:52",
   :type "course"
   },
  {
   :id "4e7b34c4-5e7a-4d60-8c3a-2f44c358ca87",
   :name "Titãs da Civilização Ocidental",
   :released_at "2019-06-18T12:30:00",
   :type "course"
   },
  {
   :id "abeff106-6b4e-4f02-9b8b-2f4844af2f49",
   :name "Brasil - A Última Cruzada",
   :released_at "2019-07-08T16:37:11",
   :type "series"
   },
  {
   :id "52707ac1-1bf3-48be-8964-30908c775c11",
   :name "Quatro Modelos de Liberdade Política",
   :released_at "2019-09-19T19:19:51",
   :type "course"
   },
  {
   :id "95f80b8c-fae2-479a-a7d4-3191178018de",
   :name "Qual o limite do Respeito?",
   :released_at "2020-03-29T20:02:34",
   :type "debate"
   },
  {
   :id "801486de-892d-4969-933d-37e12d62065d",
   :name "INTERESTELAR - Podcast Cultura Paralela #2",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "635c2198-d68e-493c-b6f0-39a54dd25556",
   :name "Filosofia Política",
   :released_at "2019-12-19T21:13:44",
   :type "course"
   },
  {
   :id "4230549d-c4f0-462c-9ce6-47450f658329",
   :name "Linguagem e Filosofia Prática",
   :released_at "2019-06-18T12:28:15",
   :type "course"
   },
  {
   :id "32645212-3dae-423d-ad01-47aa0f34b023",
   :name "Direito Constitucional",
   :released_at "2019-06-18T12:24:10",
   :type "course"
   },
  {
   :id "85009fc7-0656-457d-85af-527b7690271d",
   :name "Rap / Funk - Podcast Cultura Paralela #3",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "c4423e42-b268-47b4-a0a3-52f0ba3ec63f",
   :name "Investigação Paralela",
   :released_at "2019-11-29T20:16:45",
   :type "series"
   },
  {
   :id "97e65257-00fb-4a8f-a832-64414113e488",
   :name "O que realmente é a felicidade?",
   :released_at "2020-03-29T20:02:34",
   :type "debate"
   },
  {
   :id "ef1195f8-dd0f-429c-a26e-66f07687dbbc",
   :name "Congresso Brasil Paralelo - Rodrigo gurgel",
   :released_at "2019-01-24T11:45:57",
   :type "interview"
   },
  {
   :id "0e079413-6352-4ab7-a022-70ad2ac47633",
   :name "Introdução à Escola Austríaca de Economia",
   :released_at "2018-11-20T06:51:58",
   :type "course"
   },
  {
   :id "09f8e3e2-c799-4fc7-8734-7152eb70f313",
   :name "Ideologias Políticas As Diferentes Correntes",
   :released_at "2018-11-20T06:46:47",
   :type "course"
   },
  {
   :id "a899966e-258c-4f53-ac04-7331df602ab5",
   :name "The Witcher - Podcast Cultura Paralela #9",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "fbd86a56-e234-4804-8af0-768e92e56371",
   :name "Congresso Brasil Paralelo - Ricardo Gomes",
   :released_at "2019-01-24T11:46:22",
   :type "interview"
   },
  {
   :id "e2085f82-53b6-4eb8-a170-76ae4ee9e35a",
   :name "Congresso Brasil Paralelo - Rogerio Chequer",
   :released_at "2019-01-23T21:04:21",
   :type "interview"
   },
  {
   :id "ddcb4137-50ee-4bc6-9312-7780452e9641",
   :name "Congresso Brasil Paralelo - Arthur Moledo do Val",
   :released_at "2019-11-16T21:40:51",
   :type "interview"
   },
  {
   :id "43a90f43-608e-4b12-a512-7b394769bfbf",
   :name "Um Passeio Pela História do Liberalismo",
   :released_at "2019-06-18T12:28:49",
   :type "course"
   },
  {
   :id "cf0515b9-60c1-4aa9-815e-86cdd9878e53",
   :name "Pátria Educadora",
   :released_at "2020-03-31T02:10:38",
   :type "series"
   },
  {
   :id "b6b57db7-1d7e-459c-8bc8-871da108ebc8",
   :name "Insight Brasil Paralelo",
   :released_at "2019-10-21T23:17:13",
   :type "series"
   },
  {
   :id "4fa1bbea-7bb8-4bc2-922e-8bdc57c7ad22",
   :name "Elite Cultural e Intelectual",
   :released_at "2019-06-27T17:47:28",
   :type "course"
   },
  {
   :id "11123361-0144-406e-9285-96df174844ae",
   :name "Carlos Lacerda e a República Brasileira",
   :released_at "2018-11-21T18:32:53",
   :type "course"
   },
  {
   :id "6e4f5345-aa8c-4b49-8d55-9884f82ce23d",
   :name "A Família e a Escola na Educação",
   :released_at "2020-03-05T22:29:32",
   :type "course"
   },
  {
   :id "2edf0e71-cea1-4960-b6d6-98b2107283b4",
   :name "Arte| Imaginação e Sentido",
   :released_at "2019-06-14T18:15:50",
   :type "course"
   },
  {
   :id "b3575b60-7316-4d90-8d6a-9a4729e0d1da",
   :name "O Teatro das Tesouras",
   :released_at "2019-11-06T12:55:13",
   :type "series"
   },
  {
   :id "743d6faa-b848-4ade-ae12-9cd23dd7f2e7",
   :name "Formação da Personalidade",
   :released_at "2020-03-19T22:35:38",
   :type "course"
   },
  {
   :id "522fa35f-c0b8-4345-9d34-9da7f05c6e6e",
   :name "Mito| Linguagem e Mídia",
   :released_at "2019-07-26T13:40:36",
   :type "course"
   },
  {
   :id "c3f5cfc3-ae93-4e1f-96c9-9f850afa50a8",
   :name "Fórum Brasil a Última Cruzada - Evento 07/09/2019",
   :released_at "2019-10-25T21:23:39",
   :type "series"
   },
  {
   :id "8b05d0ce-d550-44f5-9d05-acf3a57eea37",
   :name "O Irlandês - Podcast Cultura Paralela #4",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "6705e327-577c-4f4c-8551-b5b6a5d6e28c",
   :name "Fundações do Pensamento Político Brasileiro",
   :released_at "2020-02-20T21:31:27",
   :type "course"
   },
  {
   :id "33d9c717-db56-4475-bcd0-b98dad22da59",
   :name "Desconstruindo Paulo Freire",
   :released_at "2019-06-18T12:25:56",
   :type "course"
   },
  {
   :id "d23f4bdc-aa09-46cc-8f8b-bd86c68ad1d9",
   :name "Congresso Brasil Paralelo - Rafael Nogueira",
   :released_at "2019-11-16T21:40:51",
   :type "interview"
   },
  {
   :id "b305e30b-4ea7-4381-906d-c0d68bbfa1d3",
   :name "O dia depois da eleição",
   :released_at "2019-02-04T18:32:18",
   :type "series"
   },
  {
   :id "a09de40f-0703-42c8-ab0a-c13953e2531e",
   :name "Dois Papas - Podcast Cultura Paralela #6",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "61e40563-fa4e-4058-a300-c1b4d4fef743",
   :name "As 5 Grandes Correntes da Ética no Ocidente",
   :released_at "2019-10-21T04:20:42",
   :type "course"
   },
  {
   :id "88143d8f-f8ac-419b-b7e3-c6b4681b073d",
   :name "Chernobyl - Podcast Cultura Paralela #4",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "a8051b6a-b38c-4bb4-a3b9-c980016c9224",
   :name "Era uma vez em Hollywood - Podcast Cultura Paralela #7",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "dce22ce0-4143-4b16-84bd-cffd0a815c02",
   :name "Congresso Brasil Paralelo - Alexandre Borges",
   :released_at "2019-11-16T21:40:51",
   :type "interview"
   },
  {
   :id "31bca8aa-706d-4701-a12a-d62545f0c8bf",
   :name "As Origens do Estado",
   :released_at "2019-06-17T20:17:14",
   :type "course"
   },
  {
   :id "baacc4f2-eac7-4f44-bd6c-e1d438716c2a",
   :name "1964 O Brasil entre Armas e Livros",
   :released_at "2019-07-24T20:02:34",
   :type "series"
   },
  {
   :id "a9819ee5-16b1-42c8-9b40-f5a1fa9535eb",
   :name "Congresso Brasil Paralelo",
   :released_at "2019-04-02T02:10:38",
   :type "series"
   },
  {
   :id "1defd87a-8669-4cd9-9159-f693e8f4918f",
   :name "O Que é Capitalismo?",
   :released_at "2019-01-31T03:24:59",
   :type "course"
   },
  {
   :id "79191e8e-dcfc-4380-be58-f86d2b0b7022",
   :name "Democracia em Debate",
   :released_at "2020-03-29T20:02:34",
   :type "podcast"
   },
  {
   :id "e263195f-a051-4164-990c-f91959a358b9",
   :name "Congresso Brasil Paralelo - Carlos Andreazza",
   :released_at "2018-08-16T20:12:51",
   :type "interview"
   },
  {
   :id "0f860e80-dffa-4fc5-a63f-fedd16ec0184",
   :name "O Histórico do Pensamento Liberal Brasileiro",
   :released_at "2018-11-20T06:54:23",
   :type "course"
   }
])


(defn find-all
  [request]

  (let [{{admin :admin sub :sub type :type} :identity} request
        subscription (db/get-user-active-subscription {:user_id sub})
        media (db/get-all-media)
        predicate (predicates/rbac subscription)
        ]
    (filterv predicate media)
  (println)
  (println)
  (println)
  (println)
  (println)


      (ok (filterv predicate media))
    )
  )


(defn find-one
  [{{{media_id :id} :path} :parameters}]
  {:status 200 :body {:id media_id}})
